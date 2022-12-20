package ru.otus.flightsearch.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.otus.flightsearch.configuration.BotConfig;
import ru.otus.flightsearch.converter.TickerRequestToSearchRequestDtoConverter;
import ru.otus.flightsearch.exception.WrongTicketDataException;
import ru.otus.flightsearch.model.TicketRequestModel;
import ru.otus.flightsearch.service.BotAirportsService;
import ru.otus.flightsearch.service.BotBuyerService;
import ru.otus.flightsearch.service.BotCountriesService;
import ru.otus.flightsearch.service.BotTravelPayoutService;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlightSearcherBotComponent extends TelegramLongPollingBot {

    private final BotConfig config;
    private final BotTravelPayoutService botTravelPayoutService;
    private final BotCountriesService botCountriesService;
    private final BotAirportsService botAirportsService;
    private final BotBuyerService botBuyerService;
    private final ObjectMapper objectMapper;

    private static final String SHOW_COUNTRIES = "покажи список стран";
    private static final String SHOW_TICKETS = "покажи билеты";
    private static final String SHOW_AIRPORTS = "покажи аэропорты";
    private static final String SHOW_CITIES = "покажи города";
    private static final String SAVE_TICKET = "сохрани билет";
    private static final String SHOW_SAVED_TICKETS = "покажи сохраненные билеты";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String inputMessage = message.getText();

            if (inputMessage.startsWith(SHOW_TICKETS)) {
                botBuyerService.postBuyerInfo(
                        new BuyerRecordDto(message.getChatId(),
                                message.getFrom().getFirstName(),
                                message.getFrom().getIsBot(),
                                message.getFrom().getLastName(),
                                message.getFrom().getLastName()));
                processTicketRequest(update);
            } else if (inputMessage.equals(SHOW_COUNTRIES)) {
                processCountryRequest(update);
            } else if (inputMessage.equals(SHOW_AIRPORTS)) {
                processAirportRequest(update);
            } else if (inputMessage.startsWith(SAVE_TICKET)) {
                saveTicket(update);
            } else if (inputMessage.startsWith(SHOW_SAVED_TICKETS)) {
                getSavedTickets(update);
            }
        }
    }

    private void processAirportRequest(Update update) {
        long chatId = update.getMessage().getChatId();
        sendAirportList(chatId, Lists.newArrayList(botAirportsService.getAirports()));
    }

    private void sendAirportList(long chatId, List<AirportDto> dtoList) {
        List<AirportDto> arrCopy = dtoList;

        int n = 20;
        int g = (int) Math.ceil((1.0 * arrCopy.size()) / n);

        String country;

        for (int y = 0; y < g; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            int counter = 0;
            while (counter < n) {
                if (arrCopy.isEmpty()) {
                    break;
                }
                stringBuilder.append(arrCopy.get(0).getCode()).append("\n");
                arrCopy.remove(0);
                counter++;
            }

            country = stringBuilder.toString();
            log.info(country);
            sendMessage(chatId, country);
        }
    }

    private void processCountryRequest(Update update) {
        long chatId = update.getMessage().getChatId();
        sendCountryList(chatId, Lists.newArrayList(botCountriesService.obtainCountriesList()));
    }

    private void sendCountryList(long chatId, List<CountryDto> countryDtoList) {
        List<CountryDto> arrCopy = countryDtoList;

        int n = 20;
        int g = (int) Math.ceil((1.0 * arrCopy.size()) / n);


        for (int y = 0; y < g; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            int counter = 0;
            while (counter < n) {
                if (arrCopy.isEmpty()) {
                    break;
                }
                stringBuilder.append(arrCopy.get(0).getName()).append("\n");
                arrCopy.remove(0);
                counter++;
            }

            log.info(stringBuilder.toString());
            sendMessage(chatId, stringBuilder.toString());
        }
    }

    private void saveTicket(Update update) {
        String messageTextWithOutPrefix = update.getMessage().getText().replace(SAVE_TICKET, "").trim();

        long chatId = update.getMessage().getChatId();

        if (messageTextWithOutPrefix.isEmpty()) {
            sendMessage(chatId,
                    "cannot be empty");
        } else {
            try {
                botBuyerService.saveBuyerChosenTicket(messageTextWithOutPrefix);
                sendMessage(chatId,
                        objectMapper.
                                writeValueAsString("You successfully saved the ticket: " + messageTextWithOutPrefix));
            } catch (JsonProcessingException e) {
                log.error("JsonProcessingException while saving ticket", e);
            }
        }
    }

    private void getSavedTickets(Update update) {
        long chatId = update.getMessage().getChatId();

        if (update.getMessage().getText().isEmpty()) {
            sendMessage(chatId,
                    "cannot be empty");
        } else {
            List<TicketRecordDto> orderedTickets = botBuyerService.getBuyerTickets(/*chatId*/);
            if (orderedTickets.isEmpty()) {
                sendMessage(chatId, "нет сохраненных билетов");
            }
            sendMessage(chatId, orderedTickets.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\r\n", "{", "}")));
        }
    }

    private void processTicketRequest(Update update) {
        TicketRequestModel ticketRequestModel = null;

        String messageTextWithOutPrefix = update.getMessage().getText().replace(SHOW_TICKETS, "");

        try {
            ticketRequestModel = TicketRequestModel.ofText(messageTextWithOutPrefix.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long chatId = update.getMessage().getChatId();

        try {
            SearchResultDtoList dtoTicketList = botTravelPayoutService
                    .getDtoTicketList(
                            TickerRequestToSearchRequestDtoConverter
                                    .convert(ticketRequestModel));
            sendMessage(chatId,
                    objectMapper.
                            writeValueAsString(dtoTicketList));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
        } catch (WrongTicketDataException e) {
            log.error("exception with data", e);
            sendMessage(chatId,
                    "Wrong incoming Citydata or date");
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error during register bot", e);
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}