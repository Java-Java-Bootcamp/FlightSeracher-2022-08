package ru.otus.buyer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String departCity;
    private String arriveCity;
    private String site;
    private Long price; //?
    private Timestamp departDate;
    private Timestamp returnDate;
    private Integer numberOfChanges;
    private Long duration;
    private Long distance;
}


/**
 * {
 * "departCity":"MOW",
 * "arriveCity":"KUF",
 * "site":"Fun&Sun",
 * "price":2971.0,
 * "departDate":"2023-01-16",
 * "returnDate":"2023-01-27",
 * "numberOfChanges":0,
 * "duration":225,
 * "distance":818
 * }
 */