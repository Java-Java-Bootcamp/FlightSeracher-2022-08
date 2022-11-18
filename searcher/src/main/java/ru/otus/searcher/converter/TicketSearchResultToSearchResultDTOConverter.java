package ru.otus.searcher.converter;

import ru.otus.searcher.model.Ticket;
import ru.otus.searcher.model.TicketSearchResult;
import DTO.SearchResultDto;
import DTO.SearchResultDtoList;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class TicketSearchResultToSearchResultDTOConverter {

    public SearchResultDtoList convert(TicketSearchResult result) {

        List<SearchResultDto> searchResultDtoList = new ArrayList<>();

        for (Ticket actualTicket : result.getData()) {

            SearchResultDto resultDto = SearchResultDto
                    .builder()
                    .departCity(actualTicket.getOrigin())
                    .arriveCity(actualTicket.getDestination())
                    .site(actualTicket.getGate())
                    .price(actualTicket.getValue())
                    .departDate(actualTicket.getDepartDate())
                    .returnDate(actualTicket.getReturnDate())
                    .numberOfChanges(actualTicket.getNumberOfChanges())
                    .duration(actualTicket.getDuration())
                    .distance(actualTicket.getDistance())
                    .build();
            searchResultDtoList.add(resultDto);
        }

        return new SearchResultDtoList(searchResultDtoList);
    }
}
