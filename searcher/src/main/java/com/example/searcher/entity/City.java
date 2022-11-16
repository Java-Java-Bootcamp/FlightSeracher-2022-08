package com.example.searcher.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "country_code")
    private Country country;

    @OneToMany(mappedBy = "cityOrigin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketsOrigin;

    @OneToMany(mappedBy = "cityDestination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketsDestination;
}
