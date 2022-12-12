package dto;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public record TicketRecord(@Id Long id, String departCity, String arriveCity, String site, Long price, Timestamp departDate,
                           Timestamp returnDate, Integer numberOfChanges, Long duration, Long distance) {
}
