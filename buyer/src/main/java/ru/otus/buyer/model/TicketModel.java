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
public class TicketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departCity;
    private String arriveCity;
    private String site;
    private Long price;
    private Timestamp departDate;
    private Timestamp returnDate;
    private Integer numberOfChanges;
    private Long duration;
    private Long distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_ticket_id")
    private BuyerModel buyerModel;
}
