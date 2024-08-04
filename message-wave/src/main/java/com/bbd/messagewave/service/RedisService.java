package com.bbd.messagewave.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Service
public class RedisService {
    private final Jedis jedis;

    public RedisService(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * Sets a value in Redis.
     * @param key Redis key
     * @param value Redis value
     */
    public void setValue(String key, String value) {
        jedis.set(key, value);
    }

    /**
     * Retrieves a value from Redis.
     * @param key Redis key
     * @return value or null if key does not exist
     */
    public String getValue(String key) {
        return jedis.get(key);
    }

    public Set<String> getKeys(String pattern) {
        return jedis.keys(pattern);
    }
}
