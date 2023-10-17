package com.parkjongsu.blog.config.utils;


import com.parkjongsu.blog.serve.statistics.entity.Statistics;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getData(String key){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public void setDataExpire(String key,String value,long duration){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key,value,expireDuration);
    }

    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }


    public List moveDataFromRedisToDb(String keyPattern) {
        ScanOptions scanOptions = ScanOptions.scanOptions().match(keyPattern).count(10).build();
        Cursor<byte[]> keys = stringRedisTemplate.getConnectionFactory().getConnection().scan(scanOptions);
        List list = new ArrayList();
        while (keys.hasNext()) {
            byte[] next = keys.next();
            String matchedKey = new String(next, Charsets.UTF_8);
            list.add(Statistics.builder().day(LocalDate.parse(matchedKey, DateTimeFormatter.ISO_DATE))
                    .count(Integer.parseInt(this.getData(matchedKey)))
                    .build());
            ;
            stringRedisTemplate.delete(matchedKey);
        }
        return list;

    }

}