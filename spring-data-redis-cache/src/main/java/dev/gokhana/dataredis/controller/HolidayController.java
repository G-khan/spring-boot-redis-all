package dev.gokhana.dataredis.controller;

import dev.gokhana.dataredis.model.Holiday;
import dev.gokhana.dataredis.service.HolidayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Tag(description = "Holiday related operations", name = "Holiday")
public class HolidayController {

    private final HolidayService holidayService;
    private static final Logger log = LoggerFactory.getLogger(HolidayController.class);

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping(value = "/holidays/{year}/{countryCode}")
    public ResponseEntity<Set<Holiday>> getHolidays(@PathVariable String year, @PathVariable String countryCode) {
        log.info("getHolidays called with year: {} and countryCode: {}", year, countryCode);
        return new ResponseEntity<>(holidayService.getHolidays(year, countryCode), HttpStatus.OK);
    }
}

