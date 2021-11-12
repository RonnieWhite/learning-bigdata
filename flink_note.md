*Flink执行图*
StreamGraph: 在Client上生成最初的程序执行逻辑流程，也就是算子之间的前后顺序 
JobGraph: 在Client上生成，将OneToOne的Operator合并为OperatorChain 
ExecutionGraph:在JobManager上生成，将JobGraph根据代码中设置的并行度和请求的资源进行并行化规划 
物理执行图:将ExecutionGraph的并行计划，落实到具体的TaskManager上，将具体的SubTask落实到具体的TaskSlot内进行运行

*netcat命令*
windows: nc -l -p 9999 linux: nc -l -k 9999

*运行示例作业*
flink run ../examples/streaming/SocketWindowWordCount.jar --port 9999 flink run ../examples/streaming/WordCount.jar
--input ../xxx.csv

*Flink的优势*
状态容错：精确一次保证，分布式快照（Distributed Snapshot） 可应付极大的状态量（TB+scale）：out-of-core状态后端，asynchronous快照
状态迁移：在应用重新平行化/更动应用代码的状况下仍能恢复历史状态 Event-time处理：用以定义合适接收完毕所需数据

*批处理使用DataSet API*
*流处理使用DataStream API*
keyBy()函数不能使用未重写hashCode()函数的POJO类或任意类型的数组作为入参

*指定Flink作业的运行模式*
bin/flink run -Dexecution.runtime-mode=BATCH examples/streaming/WordCount.jar 或者在代码中指定： 
StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(); 
env.setRuntimeMode(RuntimeExecutionMode.BATCH);

*大数据处理的流程*
MapReduce: input -> map(reduce) -> output Storm: input -> Spout/Bolt -> output Spark: input -> transformation/action ->
output Flink: input -> transformation/sink -> output

*Flink在算子之前交换数据时还支持的其它物理分组方式*
datastream.global(); 上游算子将所有记录发送给下游算子的第一个实例 datastream.broadcast(); 上游算子将每一条记录发送给下游算子的所有实例 datastream.forward();
只适用于上游算子实例数与下游算子相同时，每个上游算子实例将记录发送给下游算子对应的实例 datastream.shuffle(); 上游算子对每条记录随机选择一个下游算子进行发送 datastream.rebalance();
上游算子通过轮询的方式发送数据 datastream.rescale(); 当上游和下游算子的实例数为 n 或 m 时，如果 n < m，则每个上游实例向ceil(m/n)或floor(m/n)个下游实例轮询发送数据；如果 n > m，则
floor(n/m) 或 ceil(n/m) 个上游实例向下游实例轮询发送数据。 datastream.partitionCustom(new Partitioner<>())：当上述内置分配方式不满足需求时，还可以选择自定义分组方式。

*Flink中的状态*
算子状态（Operator State） 键控状态（Keyed State） 状态后端（State Backends）

*Flink编程模型*

1) 获取执行环境 2) 获取数据 3) transformation 4) sink 5) 触发执行

*course03下的代码展示了指定key的字段表达式和key选择器三种方式，分别是*

1) Tupe指定 2) 字段表达式指定 3) key选择器

*指定转换函数*

1) 直接new一个FlatMapFunction并重载flatMap方法，text.flatMap(new FlatMapFunction<String, WC>() {} 2)
   定义一个类实现FlatMapFunction接口并重载flatMap方法（myFlatMapFunction为实现类），text.flatMap(new myFlatMapFunction())
3) jdk8的匿名函数方法 4) Rich functions text.flatMap(new RichFlatMapFunction<String, WC>() {}

*flink支持的数据类型*

1) Java Tuples and Scala Case Classes 2) Java POJOs -- 类必须为public， 必须有无参构造 必须有getter和setter方法 3) Primitive Types --
   基本数据类型 4) Regular Classes -- 通用类型 5) Values 6) Hadoop Writables 7) Special Types

*DataSet API编程*

1) 数据加载 fromCollection readTextFile 解决了自己造值、csv文件读取、递归读取、以及压缩文件读取 2) transformation： map filter mapPartition(生产中用得多)
   first flatMap distinct join(leftOuterJoin rightOuterJoin fullOuterJoin)
   cross 3) 计数器 4) 分布式缓存 5) 广播变量

*DataStream API编程*

1) Flink自定义数据源：StreamExecutionEnvironment.addSource(sourceFunction)
   -- 实现SourceFunction以创建一个无并行的数据源 -- 实现ParallelSourceFunction接口 -- 继承RichParallelSourceFunction以创建一个能够并行的数据源 2)
   算子：除DataSet里所讲的之外，重点关注map和filter的连用, union, split+select 4) Flink自定义Sink： -- 自定义类继承RichSinkFunction<T> T为要写入对象的类型 --
   重写方法： -- open/close 生命周期方法 -- invoke 每条记录执行一次

*窗口Window主要分为两大类*
-- 基于时间 TimeWindow -- 基于数量 CountWindow
*Time Windows编程*

1) 处理时间类型 -- 事件时间 Event Time 生产常用，取的是数据自身带的时间戳 -- 摄取时间 Ingestion Time 数据进入flink集群的时间，在事件时间和处理时间之间，相较处理时间来说，资源耗费更大，但也更精准
   -- 处理时间 Processing Time 任务执行的时间 2) 设置处理时间类型 env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
3) 窗口分配器(WindowAssigner)：定义如何将数据分配给窗口 并指定windows是否带key(Keyed or Non-Keyed Windows)
   Non-Keyed Windows 因为无key，所以整个数据不会被并行执行 每个传入的数据被分配到一个或多个窗口 分类： -- 滚动窗口 tumbling windows 常用 固定大小，不会重叠 -- 滑动窗口 sliding
   windows 常用 固定大小，可能会重叠 -- 会话窗口 session windows -- 全局窗口 global windows 通过继承WindowAssigner, 可自定义窗口 窗口方法： reduce --
   流式，有一个计算一个 process -- 批量，等本批次数据到齐后计算一次

   窗口函数： 增量聚合函数：每条数据到来就进行计算，保持一个简单的状态：ReduceFunction, AggregateFunction
   全窗口函数：先把窗口所有数据收集起来，等到计算的时候会遍历所有数据：ProcessWindowFunction, WindowFunction

    5) 生成watermark的方法： -- 在source中，直接生成watermark，由source生成的watermark优先级比较低，可以被另一个方法产生的watermark覆盖掉 -- 通过时间戳分配器(timestamp
       assigner)来生成水印(watermark)。
    6) 时间戳分配器分两种： -- With Periodic Watermarks 周期性，一定时间间隔或一定数据量 可以定义一个最大允许乱序的时间，这种情况应用较多 -- With Punctuated Watermarks
       间断的，根据event决定是否产生新watermark

*Connectors*

1) hdfs connector 2) kafka connector

*Flink运行*

1) 启动服务：bin/start-cluster.sh 2) 运行任务：./bin/flink run examples/streaming/SocketWindowWordCount.jar --port 9000

*ON YARN是企业级用得最多的方式*
Required -n,--container <arg>   Number of YARN container to allocate (=Number of Task Managers)
Optional -D <arg>                        Dynamic properties -d,--detached Start detached -id,--applicationId <arg>
Attach to running YARN session -j,--jar <arg>                  Path to Flink jar file -jm,--jobManagerMemory <arg>
Memory for JobManager Container [in MB] JobManager的内存 -n,--container <arg>            Number of YARN container to
allocate (=Number of Task Managers) TaskManagers的个数 -nm,--name <arg>                Set a custom name for the
application on YARN -q,--query Display available YARN resources (memory, cores)
-qu,--queue <arg>               Specify YARN queue. -s,--slots <arg>                Number of slots per TaskManager
-st,--streaming Start Flink in streaming mode -t,--ship <arg>                 Ship files in the specified directory (t
for transfer)
-tm,--taskManagerMemory <arg>   Memory per TaskManager Container [in MB] Task Managers的内存 -z,--zookeeperNamespace <arg>
Namespace to create the Zookeeper sub-paths for high availability mode 1) 启动Hadoop集群： 到hadoop主目录下： sbin/start-all.sh 2)
启动flink集群： 到flink主目录下： bin/start-cluster.sh 3) 启动flink的yarn-session.sh 到flink主目录下(参数详解如上)： bin/yarn-session.sh -n 1 -jm
1024 -tm 1024 4) 跑一个示例作业： -- 先下载一个文本文档，然后传到hdfs -- 执行作业 到flink主目录下： ./bin/flink run ./examples/batch/WordCount.jar
-input hdfs://myspark:9000/LICENSE-2.0.txt -output hdfs://myspark:9000/wordcount-result.txt 在YARN上启动Flink集群 ./bin/flink
run -m yarn-cluster -yn 2 ./examples/batch/WordCount.jar 启动flink的scala shell bin/start-scala-shell.sh local

*Flink监控及调优*

1) HistoryServer 2) REST API 3) Metrics 4) 常用优化

*客户端操作*
bin/flink --help 显示flink客户端命令的帮助 bin/flink list 查看任务列表 bin/flink stop -m 127.0.0.1:8081 d67420e52bd051fae2fddbaa79e046bb
通过 -m 来指定要停止的 JobManager 的主机地址和端口 bin/flink cancel -m 127.0.0.1:8081 5e20cb6b0f357591171dfcca2eea09de 取消任务 bin/flink
cancel -m 127.0.0.1:8081 -s /tmp/savepoint 29da945b99dea6547c3fbafd57ed8759 取消任务，并显示指定 Savepoint 目录 bin/flink savepoint
-m 127.0.0.1:8081 ec53edcfaeb96b2a5dadbfbe5ff62bbb /tmp/savepoint 触发savepoint bin/flink run -h 作业运行帮助