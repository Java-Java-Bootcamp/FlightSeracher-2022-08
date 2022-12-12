package ru.otus.flightsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BuyerRecord;
import dto.TicketRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.flightsearch.configuration.BotServiceProperties;

@Service
@Slf4j
public class BotBuyerService {

    private final RestTemplate restTemplate;
    private final URIBuilder builder;
    private final BotServiceProperties botServiceProperties;
/*
    private final TicketEditor ticketEditor;
*/

    @Autowired
    public BotBuyerService(RestTemplate restTemplate, BotServiceProperties botServiceProperties/*, TicketEditor ticketEditor*/) {
        this.botServiceProperties = botServiceProperties;
        this.restTemplate = restTemplate;
        this.builder = new URIBuilder()
                .setScheme("http")
                .setHost(botServiceProperties.getBuyerDataHost())
        ;
        /*this.ticketEditor = ticketEditor;*/
    }

    public void postBuyerInfo(BuyerRecord buyerRecord) {
        BuyerRecord sendBuyerRecord = restTemplate.postForObject
                (
                        builder.setPath(
                                botServiceProperties.getBuyerDataPath() + botServiceProperties.getSavedInfo()).toString()
                        , buyerRecord
                        , BuyerRecord.class
                );
        if (sendBuyerRecord != null) {
            log.info("posted info: {}", sendBuyerRecord.id());
        }
    }

    public void saveBuyerChosenTicket(String sendTicketRecord) {
        //ticketEditor.setAsText(ticketModel);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TicketRecord ticketRecord = objectMapper.readValue(sendTicketRecord, TicketRecord.class);
            restTemplate.postForObject
                    (
                            builder.setPath(
                                    botServiceProperties.getBuyerDataPath() + "/ticket-info-save").toString()
                            , ticketRecord
                            , TicketRecord.class
                    );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

/*
        ObjectMapper objectMapper = new ObjectMapper.re(sendTicketRecord, TicketRecord.class);
*/

       /* restTemplate.postForObject(builder.setPath(
                botServiceProperties.getBuyerDataPath() + "/ticket-info-save").toString(),
                sendTicketRecord,
                String.class
                );*/
        /*restTemplate.getForObject(builder.setPath(
                botServiceProperties.getBuyerDataPath() + "/ticket-info-save").toString(),
                String.class);*/
/*        restTemplate.getForEntity(
                builder.setPath(
                        botServiceProperties.getBuyerDataPath() + "/ticket-info-save").toString()
                , AirportDto[].class).getBody();*/
    }
}
