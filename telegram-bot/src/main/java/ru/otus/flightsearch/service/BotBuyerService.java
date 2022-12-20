package ru.otus.flightsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BuyerRecordDto;
import dto.TicketRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.flightsearch.configuration.BotServiceProperties;

import java.util.List;

@Service
@Slf4j
public class BotBuyerService {

    private final RestTemplate restTemplate;
    private final URIBuilder builder;
    private final BotServiceProperties botServiceProperties;

    @Autowired
    public BotBuyerService(RestTemplate restTemplate, BotServiceProperties botServiceProperties/*, TicketEditor ticketEditor*/) {
        this.botServiceProperties = botServiceProperties;
        this.restTemplate = restTemplate;
        this.builder = new URIBuilder()
                .setScheme("http")
                .setHost(botServiceProperties.getBuyerDataHost())
        ;
    }

    public void postBuyerInfo(BuyerRecordDto buyerRecordDto) {
        BuyerRecordDto sendBuyerRecordDto = restTemplate.postForObject
                (
                        builder.setPath(
                                botServiceProperties.getBuyerDataPath() + botServiceProperties.getSavedInfoPath()).toString()
                        , buyerRecordDto
                        , BuyerRecordDto.class
                );
        if (sendBuyerRecordDto != null) {
            log.info("posted info: {}", sendBuyerRecordDto.id());
        }
    }

    public void saveBuyerChosenTicket(String sendTicketRecord) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TicketRecordDto ticketRecordDto = objectMapper.readValue(sendTicketRecord, TicketRecordDto.class);
            TicketRecordDto savedTicketRecordDto = restTemplate.postForObject
                    (
                            builder.setPath(
                                    botServiceProperties.getBuyerDataPath() + "/ticket-info-save").toString()
                            , ticketRecordDto
                            , TicketRecordDto.class
                    );
            if (savedTicketRecordDto != null) {
                log.info("posted info: {}", savedTicketRecordDto.departCity());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<TicketRecordDto> getBuyerTickets(/*Long buyerId*/) {
        TicketRecordDto[] body = restTemplate.getForEntity
                (
                        builder.setPath(
                                botServiceProperties.getBuyerDataPath() + "/tickets-info-show").toString()
                        , TicketRecordDto[].class).getBody();
        return List.of(body);
    }
}
