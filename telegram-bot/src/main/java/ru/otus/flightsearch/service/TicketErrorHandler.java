package ru.otus.flightsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.otus.flightsearch.exception.WrongTicketDataException;

import java.io.IOException;

@Component
@Slf4j
public class TicketErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.debug("Status code: " + response.getStatusCode());
            log.debug("Response" + response.getStatusText());
            log.debug(String.valueOf(response.getBody()));

            if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                log.debug("Call returned a error 400 forbidden response ");
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            log.debug(HttpStatus.BAD_REQUEST + " response. Throwing authentication exception");
            throw new WrongTicketDataException("Cannot get data due to wrong city name or date");
        }
    }
}
