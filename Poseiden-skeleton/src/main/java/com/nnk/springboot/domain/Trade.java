package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * To manipulate data about trades
 *
 */
@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false)
    private Integer id;

    @Column(nullable=false)
    private String account;

    @Column(nullable=false)
    private String type;

    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;
    private Date tradeDate;
    private String security;
    private String status;
    private String trader;
    private String benchmark;
    private String book;
    private String creationName;
    private Date creationDate;
    private String revisionName;
    private Date revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Double getBuyQuantityAsDouble(String stringBuyQuantity) {
        return Double.parseDouble(stringBuyQuantity);
    }
    public String getBuyQuantityAsString(Double doubleBuyQuantity) { return String.valueOf(doubleBuyQuantity);}

}
