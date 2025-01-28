package com.nnk.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
/**
 *
 * To collect the information about the trade from the client website to the server
 *
 */
@Data
public class TradeDTO {
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Pattern(regexp = "^[0-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    private String buyQuantity;
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

    public TradeDTO(){}

    public TradeDTO(Integer id,String account,String type,String buyQuantity){
        this.id = id;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }
}
