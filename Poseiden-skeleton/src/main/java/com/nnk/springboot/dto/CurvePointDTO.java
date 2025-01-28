package com.nnk.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

/**
 *
 * To collect the information about the curvePoint from the client website to the server
 *
 */
@Data
public class CurvePointDTO {
    private Integer id;

    @NotNull(message = "Must not be null")
    private Integer curveId;
    private Date asOfDate;

    @Pattern(regexp = "^[0-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    private String term;

    @Pattern(regexp = "^[0-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    private String value;
    private Date creationDate;

    public CurvePointDTO(Integer id,Integer curveId, String term, String value){
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePointDTO(){

    }
}
