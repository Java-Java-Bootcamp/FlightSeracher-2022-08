package dto;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public record TicketRecordDto(@Id Long id, String departCity, String arriveCity, String site, Long price, Timestamp departDate,
                              Timestamp returnDate, Integer numberOfChanges, Long duration, Long distance) {

    @Override
    public String toString() {
        return "TicketInformation: " +
                "id=" + id +
                ", departCity='" + departCity + '\'' +
                ", arriveCity='" + arriveCity + '\'' +
                ", site='" + site + '\'' +
                ", price=" + price +
                ", departDate=" + departDate +
                ", returnDate=" + returnDate +
                ", numberOfChanges=" + numberOfChanges +
                ", duration=" + duration +
                ", distance=" + distance +
                '}';
    }
}
