package ru.otus.flightsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TicketRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import ru.otus.flightsearch.model.TicketModel;

import java.beans.PropertyEditorSupport;

@RequiredArgsConstructor
public class TicketEditor extends PropertyEditorSupport{

    private final ObjectMapper objectMapper;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            TicketModel ticketModel = new TicketModel();
            try {
                ticketModel = objectMapper.readValue(text, TicketModel.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(ticketModel);
        }
    }
}