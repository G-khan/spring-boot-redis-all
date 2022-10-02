package dev.gokhana.dataredis.client;

import dev.gokhana.dataredis.model.Holiday;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class HolidayClient {

    private final RestTemplate restTemplate;

    public HolidayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // get url from properties file
    @Value("${holiday.url}")
    private String url;

    // TODO: Handle error scenarios.
    public Set<Holiday> getHolidays(String year, String countryCode) {
        url += year + "/" + countryCode;
        return restTemplate.getForObject(url, Set.class);
    }
}
