package com.nnk.springboot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import java.sql.Date;

/**
 *
 * To collect the information about the bidList from the client website to the server
 *
 */
@Data
public class BidListDTO {
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Pattern(regexp = "^[0-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    @Max(value = 100, message = "The value must be less than 101")
    private String bidQuantity;
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


    public BidListDTO() {}

    public BidListDTO(Integer id, String account, String type, String bidQuantity) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
