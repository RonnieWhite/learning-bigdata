*Spark的安全性设置，默认为off（不安全）*
    Property Name	Default	Meaning
    spark.authenticate	false	Whether Spark authenticates its internal connections.
    spark.authenticate.secret	None	The secret key used authentication. See above for when this configuration should be set.

*Spark运行模式*
    1) local
    2) standalone
    3) cluster

*提交spark任务进行运行*
    ./bin/spark-submit \
      --class <main-class> \  // 主类，如： org.apache.spark.examples.SparkPi
      --master <master-url> \ // ip,如：spark://23.195.26.187:7077
      --deploy-mode <deploy-mode> \   // 运行模式，如：cluster  (default: client)
      --conf <key>=<value> \  // 配置，键值对的方式
      <application-jar> \     // jar包，如：examples/jars/spark-examples_2.11-2.2.1.jar
      [application-arguments] // 运行参数

*广播变量的注意事项*
    1) 不能将一个RDD使用广播变量广播出去，因为RDD不存储数据，可以将RDD的结果广播出去
    2) 广播变量只能在Driver端定义，不能在Executor端定义
    3) 在Driver端可以修改广播变量的值，在Executor端无法修改广播变量的值
    4) 如果Executor端用到了Driver的变量，如果不使用广播变量在Executor有多少个Task，就有多少个Driver端的变量副本
    5) 如果Executor端用到了Driver的变量，如果使用广播变量，则在每个Executor中只有一份Driver端的变量副本
*累加器*
    1) 累加器在Driver端定义赋初始值，累加器只能在Driver端读取最后的值，在Executor端更新
    2) 累加器不是一个调优的操作，因为如果不这样做，结果是错的

*缓存和持久化*
   1) 调用RDD.persist()或RDD.cache() 其实cache()也是调用的persist(),
      存储等级默认为MEMORY_ONLY所以如果调用persist(),参数选择会更灵活
   2) reduceByKey() reduceByWindow() reduceByKeyAndWindow()会自定调用存储

*Checkpoint机制*
   1) 重写Driver方法
   2) 配置Checkpoint

*启用预写日志机制（WAL Write Ahead Log）*
   1) 调用StreamingContext的checkpoint()方法设置一个checkpoint目录，
   2) 然后需要将Spark.streaming.receiver.writeAheadLog.enable参数设置为true
   这种极强的可靠性机制会导致Receiver的吞吐量大幅下降，因为单位时间内有相当一部分时间需要将数据写入预写日志
   如果又希望开启预写日志机制，确保数据零损失，又不希望影响系统的吞吐量，那么可以创建多个输入DStream，启动多个Receiver
   此外，在启动了预写日志机制后，推荐将复杂持久化机制禁用掉，因为所有数据已经保存在容错的文件系统中，不需要再复制机制进行
   持久化，保存一份副本了，只要将DStream的持久化机制设置一下即可：persist(StorageLevel.MEMORY_AND_DISK_SER)
   (默认是基于复制的持久化策略)
*设置Receiver接收速度*
   如果集群资源有限，并没有大到足以让应用程序一接收到数据就立即处理它，Receiver可以被设置一个最大接收限速，以每秒接收多少条单位来限速。
   spark.streaming.receiver.maxRate和spark.streaming.kafka.maxRatePerPartition参数可以用来设置
   前者设置普通Receiver，后者是Kafka Direct方式（推荐后者）
   Spark1.5后，对于Kafka Direct方式，引用了backpressure机制，从而不需要设置Receiver的限速，
   Spark可以自动估计Receiver最合理的接收速度，并根据情况动态调整，只要将
   spark.streaming.backpressure.enabled设置为true
   即可

*Spark SQL支持两种转换RDD为Dataset的方法*
    1) 利用反射机制：
        这种方式代码简洁，在已知schema的情况下较方便
    2) 构造schema，然后运用到RDD上
        这种方法比较复杂，支持在运行前不知列和类型时构造dataset
    Spark SQL支持JavaBeans RDD向DataFrame的自动转换
    目前最新版本的spark（2.4.5） Spark SQL不支持JavaBeans字段数据类型为map， 可支持list和array类型以及实现了序列化的JavaBeans

*SparkSQL连接hive*
    1) hive-site.xml添加配置：
        <property>
          <name>hive.metastore.uris</name>
          <value>thrift://myspark:9083</value>
        </property>
        <property>
          <name>hive.metastore.schema.verification</name>
          <value>false</value>
        </property>
    2) 拷贝hive-site.xml以及hadoop的core-site.xml hdfs-site.xml到spark的conf/目录下
    3) 启动hadoop集群
    4) 启动hive的metastore服务：bin/hive --service metastore
    5) 服务器：启动spark-shell验证结果
       idea开发时：
           将上述的core-site.xml hdfs-site.xml hive-site.xml拷贝到src/main/resources目录下；
           pom.xml引入spark-hive的依赖：
                   <dependency>
                       <groupId>org.apache.spark</groupId>
                       <artifactId>spark-hive_2.11</artifactId>
                       <version>${spark.version}</version>
                   </dependency>
       然后：
           SparkSession spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();
           // 查询hive表数据
           spark.sql("SELECT * FROM ods_weblog_origin").show();
		   
*Spark Streaming消费kafka的两种模式*
    -- Receiver模式，又称kafka高级api模式：简单理解就是kafka把消息全部封装好，提供给spark去调用，本来kafka的消息分布在不同的partition上，
	    相当于做了一步数据合并后再发送给spark，故spark可以设置executor个数去消费这部分数据，效率相对慢些
	-- Direct模式，又称kafka低级API模式：简单的理解就是spark直接从kafka底层中的partition直接获取消息，相对于Receiver模式少了一步，
	    效率更快，但是这样一来spark中的executor的工作的个数就与kafka中partition一致。设置再多的executor都不工作，同时偏移量也需要自己维护

*Spark Input source format*
    -- File source - Reads files written in a directory as a stream of data. Supported file formats are text, csv, json, orc, parquet. See the docs of the DataStreamReader interface for a more up-to-date list, and supported options for each file format. Note that the files must be atomically placed in the given directory, which in most file systems, can be achieved by file move operations.
    -- Kafka source - Reads data from Kafka. It’s compatible with Kafka broker versions 0.10.0 or higher. See the Kafka Integration Guide for more details.
    -- Socket source (for testing) - Reads UTF8 text data from a socket connection. The listening server socket is at the driver. Note that this should be used only for testing as this does not provide end-to-end fault-tolerance guarantees.
    -- Rate source (for testing) - Generates data at the specified number of rows per second, each output row contains a timestamp and value. Where timestamp is a Timestamp type containing the time of message dispatch, and value is of Long type containing the message count, starting from 0 as the first row. This source is intended for testing and benchmarking.