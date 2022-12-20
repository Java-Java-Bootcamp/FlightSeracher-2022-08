package ru.otus.buyer.service;

import dto.AirportDto;
import dto.BuyerRecordDto;
import dto.TicketRecordDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.otus.buyer.model.BuyerModel;
import ru.otus.buyer.model.TicketModel;
import ru.otus.buyer.repository.BuyerRepository;
import ru.otus.buyer.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SuppressWarnings("ConstantConditions")
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final TicketRepository ticketRepository;
    private final Converter<BuyerRecordDto, BuyerModel> buyerRecordBuyerConverter;
    private final Converter<TicketRecordDto, TicketModel> ticketRecordDtoTicketModelConverter;
    private final Converter<TicketModel, TicketRecordDto> ticketModelTicketRecordDtoConverter;

    public void saveUserInfo(BuyerRecordDto buyerRecordDto) {
        buyerRepository.save(buyerRecordBuyerConverter.convert(buyerRecordDto));
    }

    public void saveTicketData(TicketRecordDto ticketRecordDto) {
        ticketRepository.save(ticketRecordDtoTicketModelConverter.convert(ticketRecordDto));
    }

    public List<TicketRecordDto> getTickets(/*Long buyerId*/) {
        return ticketRepository.findAll().stream().map(ticketModelTicketRecordDtoConverter::convert).toList();
//        return ticketRepository.findById(buyerId).stream().toList();
    }
}
