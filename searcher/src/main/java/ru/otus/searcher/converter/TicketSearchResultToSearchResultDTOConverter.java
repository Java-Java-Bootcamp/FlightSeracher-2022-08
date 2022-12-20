package ru.otus.searcher.converter;

import dto.SearchResultDto;
import dto.SearchResultDtoList;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.searcher.model.TicketModel;
import ru.otus.searcher.model.TicketSearchResultModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketSearchResultToSearchResultDTOConverter implements Converter<TicketSearchResultModel, SearchResultDtoList> {

    @Override
    public SearchResultDtoList convert(TicketSearchResultModel result) {
        List<SearchResultDto> searchResultDtoList = new ArrayList<>();

        for (TicketModel actualTicketModel : result.getData()) {
            SearchResultDto resultDto = SearchResultDto
                    .builder()
                    .departCity(actualTicketModel.getOrigin())
                    .arriveCity(actualTicketModel.getDestination())
                    .site(actualTicketModel.getGate())
                    .price(actualTicketModel.getValue())
                    .departDate(actualTicketModel.getDepartDate())
                    .returnDate(actualTicketModel.getReturnDate())
                    .numberOfChanges(actualTicketModel.getNumberOfChanges())
                    .duration(actualTicketModel.getDuration())
                    .distance(actualTicketModel.getDistance())
                    .build();
            searchResultDtoList.add(resultDto);
        }

        return new SearchResultDtoList(searchResultDtoList);
    }
}
