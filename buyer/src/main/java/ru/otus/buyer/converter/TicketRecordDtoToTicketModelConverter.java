package ru.otus.buyer.converter;

import dto.TicketRecordDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.buyer.model.TicketModel;

@Component
public class TicketRecordDtoToTicketModelConverter implements Converter<TicketRecordDto, TicketModel> {

    @Override
    public TicketModel convert(TicketRecordDto ticketRecord) {
        return new TicketModel()
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