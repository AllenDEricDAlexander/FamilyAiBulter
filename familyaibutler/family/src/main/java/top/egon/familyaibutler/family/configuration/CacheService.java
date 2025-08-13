package top.egon.familyaibutler.family.configuration;

import com.google.common.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import top.egon.familyaibutler.common.pojo.CacheMessage;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.configuration
 * @ClassName: CacheService
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-13Day-16:48
 * @Description: CacheService
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class CacheService {
    private final Cache<String, Object> guavaCache;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    public <T> T get(String key, Class<T> clazz) {
        Object localValue = guavaCache.getIfPresent(key);
        return clazz.cast(localValue);

    }

    public void put(String key, Object value, long ttlSeconds) {
        guavaCache.put(key, value);
        redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
        redisTemplate.convertAndSend(topic.getTopic(), new CacheMessage(key, "update", String.valueOf(value)));
    }

    public void evict(String key) {
        guavaCache.invalidate(key);
        redisTemplate.delete(key);
        redisTemplate.convertAndSend(topic.getTopic(), new CacheMessage(key, "evict", null));
    }

}