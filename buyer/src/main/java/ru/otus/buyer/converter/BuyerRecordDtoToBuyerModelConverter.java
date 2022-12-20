package ru.otus.buyer.converter;

import dto.BuyerRecordDto;
import dto.TicketRecordDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.buyer.model.BuyerModel;
import ru.otus.buyer.model.TicketModel;

@Component
public class BuyerRecordDtoToBuyerModelConverter implements Converter<BuyerRecordDto, BuyerModel> {

    @Override
    public BuyerModel convert(BuyerRecordDto buyerRecordDto) {
        return new BuyerModel()
                .setId(buyerRecordDto.id())
                .setFirstName(buyerRecordDto.firstName())
                .setBot(buyerRecordDto.isBot())
                .setUserName(buyerRecordDto.userName())
                ;
    }
}