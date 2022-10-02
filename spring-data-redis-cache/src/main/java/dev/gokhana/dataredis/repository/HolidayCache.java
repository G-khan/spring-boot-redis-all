package dev.gokhana.dataredis.repository;

import dev.gokhana.dataredis.model.Holiday;
import dev.gokhana.dataredis.util.TimeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class HolidayCache {
    public static final String HASH_KEY = "Holiday";

    private final RedisTemplate<String, Object> template;

    public HolidayCache(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    /**
     * Save holidays to redis cache with expiration time that calculated by TimeUtil getDaysUntilEndOfYear method
     *
     * @param holidays
     */
    public void saveWithExpire(String key, Set<Holiday> holidays) {
        template.opsForHash().put(HASH_KEY, key, holidays);
        template.expire(HASH_KEY, TimeUtil.getDaysUntilEndOfYear() - 1, TimeUnit.DAYS);
    }


    public List<Object> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    @SuppressWarnings("unchecked")
    public Set<Holiday> findHistoryByKey(String key) {
        return (Set<Holiday>) template.opsForHash().get(HASH_KEY, key);
    }


    public void deleteHistory(int id) {
        template.opsForHash().delete(HASH_KEY, id);
    }

    // get data from specific range
    public List<Object> findHistoryByRange(int start, int end) {
        // print size of history
        long sizeOfHistory = template.opsForHash().size(HASH_KEY);
        System.out.println("Size of history: " + sizeOfHistory);
        // if the size in the range start and end, return the data
        if (sizeOfHistory >= start && sizeOfHistory <= end) {
            return template.opsForList().range(HASH_KEY, start, end);
        }
        // if the size not in the range start and end, return null
        return Collections.emptyList();
    }

    public boolean isExists(String key) {
        return template.opsForHash().hasKey(HASH_KEY, key);
    }

    // redis left push
    public void leftPush(Set<Holiday> holidays) {
        System.out.println("Left push: ");
        template.opsForList().leftPop("leftPush");
        template.opsForList().leftPush(HASH_KEY,"leftPush", holidays);
    }

    // get data by index
    public Object findHistoryByIndex(int index) {
        return template.opsForList().index(HASH_KEY, index);
    }

    // redis right push
    public void rightPush(Set<Holiday> holidays) {
        System.out.println("rightPush");
        template.opsForList().rightPush(HASH_KEY,"rightPush", holidays);
    }

}
