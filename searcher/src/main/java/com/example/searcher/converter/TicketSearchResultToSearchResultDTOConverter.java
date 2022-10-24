package com.example.searcher.converter;

import com.example.searcher.model.Ticket;
import com.example.searcher.model.TicketSearchResult;
import com.example.searcher.dtos.SearchResultDto;
import com.example.searcher.dtos.SearchResultDtoList;
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
                    .localDateTime(actualTicket.getFoundAt())
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