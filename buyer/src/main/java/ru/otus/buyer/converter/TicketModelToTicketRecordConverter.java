package ru.otus.buyer.converter;

import dto.TicketRecordDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.buyer.model.TicketModel;


@Component
public class TicketModelToTicketRecordConverter implements Converter<TicketModel, TicketRecordDto> {

    @Override
    public TicketRecordDto convert(TicketModel ticketModel) {
        return new TicketRecordDto(
                ticketModel.getId(),
                ticketModel.getDepartCity(),
                ticketModel.getArriveCity(),
                ticketModel.getSite(),
                ticketModel.getPrice(),
                ticketModel.getDepartDate(),
                ticketModel.getReturnDate(),
                ticketModel.getNumberOfChanges(),
                ticketModel.getDuration(),
                ticketModel.getDistance()
        );
    }
}