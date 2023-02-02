# learning-bigdata

hadoop+spark+flink

scala版本：2.11.12
jdk版本：1.8.0_251
hadoop版本：2.7.3
spark版本：2.3.4
flink版本：flink-1.10.0-bin-scala_2.11
hbase版本：1.4.13
kafka版本：kafka_2.11-1.0.1

已改用maven
src/main/java/和src/main/scala/为sources root，分别放java、scala的代码。

# mysql bin-log

## 查看binlog是否开启

```sql
SHOW VARIABLES LIKE 'log_bin%';
```

## 开启binlog

1. 关闭MySQL

```shell
systemctl stop mysqld.service
```

2. 修改配置文件

```shell
vim /etc/my.cnf
```

在[mysqld]模块下添加：

```text
# 开启 Binlog 并写明存放日志的位置
log_bin = /usr/local/mysql/log/bin-log
# 指定索引文件的位置
log_bin_index = /usr/local/mysql/log/mysql-bin.index
#删除超出这个变量保留期之前的全部日志被删除
expire_logs_days = 7
# 指定一个集群内的 MySQL 服务器 ID，如果做数据库集群那么必须全局唯一，一般来说不推荐 指定 server_id 等于 1。
server_id = 1
# 设置方面提到过的三种 Binlog 的日志模式
binlog_format = ROW
```

3. 启动MySQL

```shell
systemctl start mysqld.service
```
4. 可能存在log_bin无权限的情况（用户在目录/usr/local/mysql/log）无足够权限，可执行以下命令后再启动MySQL服务：
```shell
chgrp -R mysql /usr/local/mysql && chown -R mysql /usr/local/mysql
```
另，MySQL日志可通过查看配置文件（/etc/my.cnf）查看，默认为：/var/log/mysqld.log