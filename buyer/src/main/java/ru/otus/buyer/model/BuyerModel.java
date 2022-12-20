package ru.otus.buyer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class BuyerModel {

    @Id
    private Long id;

    private String firstName;
    private boolean isBot;
    private String lastName;
    private String userName;

    @OneToMany(mappedBy = "buyerModel", fetch = FetchType.LAZY)
    private List<TicketModel> ticketModels;
}
