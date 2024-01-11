package com.echo.feature.boot.config.cache;

import com.echo.feature.boot.NewFeatureApplicationTests;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/4 16:48
 */
public class CaffeineCacheTest extends NewFeatureApplicationTests {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManager() throws InterruptedException {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("caffeineCache");
        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
        nativeCache.put("key", "value");

        Thread t1 = new Thread(() -> {
            CaffeineCache inCache = (CaffeineCache) cacheManager.getCache("caffeineCache");
            Cache<Object, Object> inNativeCache = inCache.getNativeCache();
            Object val = inNativeCache.getIfPresent("key");
            System.out.println(val);
        });

        t1.start();
        t1.join();

        nativeCache.invalidate("key");
        t1 = new Thread(() -> {
            CaffeineCache inCache = (CaffeineCache) cacheManager.getCache("caffeineCache");
            Cache<Object, Object> inNativeCache = inCache.getNativeCache();
            Object val = inNativeCache.getIfPresent("key");
            System.out.println(val);
        });
        t1.start();
        t1.join();


        for (int i = 0; i < 15; i++) {
            nativeCache.put("key" + i, "value" + i);
        }
        t1 = new Thread(() -> {
            CaffeineCache inCache = (CaffeineCache) cacheManager.getCache("caffeineCache");
            Cache<Object, Object> inNativeCache = inCache.getNativeCache();
            for (Map.Entry<Object, Object> entryCache : inNativeCache.asMap().entrySet()) {
                System.out.println(entryCache.getKey() + ":" + entryCache.getValue());
            }
        });
        t1.start();
        t1.join();
    }

    public static void main(String[] args) {
        Cache<String, Object> localCache = Caffeine.newBuilder().initialCapacity(5).maximumSize(10).build();

        localCache.put("key", "value");
        System.out.println(localCache.getIfPresent("key"));

        localCache.invalidate("key");
        System.out.println(localCache.getIfPresent("key"));

        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            localCache.put("key" + i, "value" + i);
            keys.add("key" + i);
        }

        Map<String, Object> allPresent = localCache.getAllPresent(keys);
        for (Map.Entry<String, Object> entryCache : allPresent.entrySet()) {
            System.out.println(entryCache.getKey() + ":" + entryCache.getValue());
        }

        System.out.println("=======================");

        for (Map.Entry<String, Object> entryCache : localCache.asMap().entrySet()) {
            System.out.println(entryCache.getKey() + ":" + entryCache.getValue());
        }

        System.out.println(localCache.estimatedSize());
    }
}
