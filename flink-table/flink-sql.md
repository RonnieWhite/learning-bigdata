# 建表

## source为filesystem

```sql
CREATE TABLE client_info(
    client_id VARCHAR,
    client_name VARCHAR,
    salary INT
)WITH(
    'connector' = 'filesystem',
    'path' = 'file:///root/data/client_info.csv',
    'format' = 'csv'
);
```

## 指定watermark

```sql
CREATE TABLE bid(
    bidtime TIMESTAMP(3),
    price DECIMAL(10, 2),
    item STRING,
    WATERMARK FOR bidtime AS bidtime - INTERVAL '10' MINUTES
)WITH(
    'connector' = 'filesystem',
    'path' = 'file:///root/data/bid.csv',
    'format' = 'csv'
);
```

## 建表时指定watermark，source为filesystem，且提取的时间字段为BIGINT类型的时间戳

```sql
CREATE TABLE bid_ltz(
    bidtime BIGINT,
    bidtime_ltz AS TO_TIMESTAMP_LTZ(bidtime, 3),
    price DECIMAL(10, 2),
    item STRING,
    WATERMARK FOR bidtime_ltz AS bidtime_ltz - INTERVAL '10' MINUTES
)WITH(
    'connector' = 'filesystem',
    'path' = 'file:///root/data/bid_ltz.csv',
    'format' = 'csv'
);
```

## 建表时指定watermark，source为Kafka

```sql
CREATE TABLE client_info(
    id INT,
    name STRING,
    score INT,
    row_time TIMESTAMP(3),
    WATERMARK FOR row_time AS row_time - INTERVAL '5' SECOND
)WITH(
    'connector'='kafka',
    'topic'='client_info',
    'properties.bootstrap.servers'='vm01:9092',
    'properties.group.id'='testGroup',
    'scan.startup.mode'='latest-offset',
    'format'='csv'
);
```

# 查询语句

```sql
-- 滚动窗口。Hopping windows are also known as “sliding windows” 执行建表语句->filesystem数据源
-- TUMBLE(TABLE data, DESCRIPTOR(timecol), size)
-- HOP(TABLE data, DESCRIPTOR(timecol), slide, size [, offset ])
-- CUMULATE(TABLE data, DESCRIPTOR(timecol), step, size)
SELECT window_start, window_end, SUM(price) FROM TABLE
(TUMBLE(TABLE bid, DESCRIPTOR(bidtime), INTERVAL '10' MINUTES))
--(HOP(TABLE bid, DESCRIPTOR(bidtime), INTERVAL '5' MINUTES, INTERVAL '10' MINUTES))
--(CUMULATE(TABLE bid, DESCRIPTOR(bidtime), INTERVAL '5' MINUTES, INTERVAL '10' MINUTES))
GROUP BY window_start, window_end;
```

# Processing Time

## 建表,row_time将取系统时间，不需要特别指定字段

```sql
CREATE TABLE process_time(
    id INT,
    name STRING,
    salary DECIMAL(10,2),
    row_time AS PROCTIME()
)WITH(
    'connector'='kafka',
    'topic'='process_time',
    'properties.bootstrap.servers'='vm01:9092',
    'properties.group.id'='testGroup',
    'scan.startup.mode'='latest-offset',
    'format'='csv'
);
```

## 查询语句，滚动窗口

```sql
SELECT TUMBLE_START(row_time, INTERVAL '10' SECONDS), 
    SUM(salary)
FROM process_time
GROUP BY TUMBLE(row_time, INTERVAL '10' SECONDS)
;
```

# 分区表
```sql
CREATE TABLE client_info(
    id INT,
    name STRING,
    score INT,
    row_time TIMESTAMP(3),
    WATERMARK FOR row_time AS row_time - INTERVAL '5' SECOND
)WITH(
    'connector'='kafka',
    'topic'='client_info',
    'properties.bootstrap.servers'='vm01:9092',
    'properties.group.id'='testGroup',
    'scan.startup.mode'='latest-offset',
    'format'='csv'
);
```



