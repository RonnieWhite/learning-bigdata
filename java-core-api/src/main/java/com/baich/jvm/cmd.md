# 启动jar包程序

    -- 指定GC堆大小（大小值设置为相同值即为不可扩展），打印GC信息
    java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -jar demo-1.0-SNAPSHOT.jar
   
     -- dump出快照文件    
    java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:SurvivorRatio=8 -jar demo-1.0-SNAPSHOT.jar

# 常用参数使用案例
    -Xms20M 设置初始堆为20M
    -Xmx20M 设置最大堆为20M
    -Xss128k 设置每个线程的堆栈大小为128K