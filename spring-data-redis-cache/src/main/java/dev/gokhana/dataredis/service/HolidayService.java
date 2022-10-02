package dev.gokhana.dataredis.service;

import dev.gokhana.dataredis.model.Holiday;

import java.util.Set;

public interface HolidayService {
    Set<Holiday> getHolidays(String year, String countryCode);
}
