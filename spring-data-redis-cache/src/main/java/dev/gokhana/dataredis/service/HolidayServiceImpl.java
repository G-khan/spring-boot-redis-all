package dev.gokhana.dataredis.service;

import dev.gokhana.dataredis.client.HolidayClient;
import dev.gokhana.dataredis.model.Holiday;
import dev.gokhana.dataredis.repository.HolidayCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HolidayServiceImpl implements HolidayService {

    // inject User repository
    private final HolidayCache holidayCache;
    private final HolidayClient holidayClient;


    public HolidayServiceImpl(HolidayCache holidayCache, HolidayClient holidayClient) {
        this.holidayCache = holidayCache;
        this.holidayClient = holidayClient;
    }

    // create a logger
    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);


    @Override
    public Set<Holiday> getHolidays(String year, String countryCode) {
        Set<Holiday> currentHolidays = holidayCache.findHistoryByKey(year + countryCode);
        if (currentHolidays != null && !currentHolidays.isEmpty()) {
            logger.info("getHolidays called with year: {} and countryCode: {} from cache", year, countryCode);
            return currentHolidays;
        }
        currentHolidays = holidayClient.getHolidays(year, countryCode);
        holidayCache.saveWithExpire(year + countryCode, currentHolidays);
        logger.info("Holidays are saved to redis cache:{} ", currentHolidays);
        return currentHolidays;
    }
}
