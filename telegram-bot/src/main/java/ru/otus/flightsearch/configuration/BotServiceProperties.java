package ru.otus.flightsearch.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "botservice")
public class BotServiceProperties {

    private String travelPayoutDataHost;
    private String airportPath;
    private String countriesPath;
    private String citiesPath;
    private String ticketsPath;
    private String buyerDataHost;
    private String buyerDataPath;
    private String savedInfoPath;
}


