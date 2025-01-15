package com.nnk.springboot.domain;

/*import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;*/
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
//import java.sql.Timestamp;

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

    @NotBlank(message = "Account is mandatory")
    @Column(nullable=false)
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(nullable=false)
    private String type;

    /*@Pattern(regexp = "^[0-9]*$",
            message = "Only numbers can be typed")*/
    @Pattern(regexp = "^[1-9][0-9]*$",
            message = "Only numbers can be typed")
    //@DecimalMin(value = "1", message = "The value must be 1 or more")
    private Long bidQuantity;
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
}
