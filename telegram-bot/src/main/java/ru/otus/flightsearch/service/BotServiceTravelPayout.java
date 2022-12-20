package ru.otus.flightsearch.service;

import dto.SearchRequestDto;
import dto.SearchResultDtoList;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.flightsearch.configuration.BotServiceProperties;

@Service
@Slf4j
public class BotServiceTravelPayout {

    private final RestTemplate restTemplate;
    private final URIBuilder builder;

    @Autowired
    public BotServiceTravelPayout(RestTemplateBuilder restTemplateBuilder, BotServiceProperties botServiceProperties) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new TicketErrorHandler())
                .build();

        this.builder = new URIBuilder()
                .setScheme("http")
                .setHost(botServiceProperties.getTravelPayoutDataHost())
                .setPath(botServiceProperties.getTicketsPath());
    }

    public SearchResultDtoList getDtoTicketList(SearchRequestDto requestDto) {
        SearchResultDtoList searchResultDtoList;
        searchResultDtoList = restTemplate
                .postForEntity(builder.toString(), requestDto, SearchResultDtoList.class).getBody();
        if (searchResultDtoList != null) {
            log.info("body: {}", searchResultDtoList.toString());
        }
        return searchResultDtoList;
    }
}

