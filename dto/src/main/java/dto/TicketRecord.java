package dto;

import org.springframework.data.annotation.Id;

public record TicketRecord(@Id Long id, String departCity, String arriveCity, String site, String price, String departDate,
                           String returnDate, String numberOfChanges, String duration, String distance) {
}
