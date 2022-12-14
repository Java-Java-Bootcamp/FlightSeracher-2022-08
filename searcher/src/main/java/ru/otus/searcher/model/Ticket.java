package ru.otus.searcher.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ticket {
    private long value;
    private String tripClass;
    private String origin;
    private String destination;
    private String gate;
    private LocalDate departDate;
    private LocalDate returnDate;
    private int numberOfChanges;
    private long duration;
    private long distance;
}
