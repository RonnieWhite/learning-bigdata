package com.baich.flink.java.udp.kafka2redis;

import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-08
 * Time : 20:32
 * Description: Kafka2RedisDemo RedisMapper
 * Modified By:
 * version : v1.0
 */
public class MyRedisMapper implements RedisMapper<String> {
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.HSET, "user_name");
    }

    public String getKeyFromData(String s) {
        String[] fields = s.split(",");
        return fields[0];
    }

    public String getValueFromData(String s) {
        String[] fields = s.split(",");
        return fields[1];
    }
}
