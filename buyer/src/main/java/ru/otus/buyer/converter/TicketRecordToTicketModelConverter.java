package ru.otus.buyer.converter;

import dto.TicketRecord;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.buyer.model.Ticket;

@Component
public class TicketRecordToTicketModelConverter implements Converter<TicketRecord, Ticket> {

    @Override
    public Ticket convert(TicketRecord ticketRecord) {
        return new Ticket()
                .setId(ticketRecord.id())
                .setDepartCity(ticketRecord.departCity())
                .setArriveCity(ticketRecord.arriveCity())
                .setSite(ticketRecord.site())
                .setPrice(ticketRecord.price())
                .setDepartDate(ticketRecord.departDate())
                .setReturnDate(ticketRecord.returnDate())
                .setNumberOfChanges(ticketRecord.numberOfChanges())
                .setDuration(ticketRecord.duration())
                .setDistance(ticketRecord.distance())
                ;
    }
}