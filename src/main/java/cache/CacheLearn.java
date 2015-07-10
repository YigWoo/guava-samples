package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Under guava-samples
 * Created by yichao on 7/10/2015.
 */
public class CacheLearn {

    public static String computeLargeString(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for (int i = 0; i < 10000; i++) {
            sb.append('a');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return computeLargeString(key);
                    }
                });

    }
}
