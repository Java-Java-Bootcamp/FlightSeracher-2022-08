package ru.otus.buyer.service;

import dto.BuyerRecord;
import dto.TicketRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.otus.buyer.model.Buyer;
import ru.otus.buyer.model.Ticket;
import ru.otus.buyer.repository.BuyerRepository;
import ru.otus.buyer.repository.TicketRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@SuppressWarnings("ConstantConditions")
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final TicketRepository ticketRepository;
    private final Converter<BuyerRecord, Buyer> buyerRecordBuyerConverter;
    private final Converter<TicketRecord, Ticket> ticketRecordTicketConverter;

    public void saveUserInfo(BuyerRecord buyerRecord) {
        buyerRepository.save(buyerRecordBuyerConverter.convert(buyerRecord));
    }

    public void saveTicketData(TicketRecord ticketRecord) {
        ticketRepository.save(ticketRecordTicketConverter.convert(ticketRecord));
        //return ticketRepository.save(ticketRecordTicketConverter.convert(ticketRecord));
        //Ticket сверху
    }
}
