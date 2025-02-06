package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 *
 * To manipulate data about bidLists
 *
 */
@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false)
    private Integer id;

    @Column(nullable=false)
    private String account;

    @Column(nullable=false)
    private String type;

    private Double bidQuantity;
    private Long askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Date bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Date creationDate;
    private String revisionName;
    private Date revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Double getBidQuantityAsDouble(String stringBigQuantity) {
        return Double.parseDouble(stringBigQuantity);
    }
    public String getBidQuantityAsString(Double doubleBigQuantity) { return String.valueOf(doubleBigQuantity);}
}
