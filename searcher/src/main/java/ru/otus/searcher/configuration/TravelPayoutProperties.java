package ru.otus.searcher.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "travelpayout")

public class TravelPayoutProperties {
    private String token;
    private String url;
    private String searchPath;
    private String countriesPath;
    private String citiesPath;
    private String airportsPath;
}

/*
https://api.travelpayouts.com/aviasales_resources/v3/airports.json
http://localhost:8080/api/airports

 */
