package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

//import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @NotNull(message = "Must not be null")
    private Integer CurveId;
    private Date asOfDate;

    @Pattern(regexp = "^[1-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    private String term;

    @Pattern(regexp = "^[1-9]+(.[0-9]+)?$",message = "Only numbers can be typed")
    @Min(value = 1, message = "The value must be 1 or more")
    private String value;
    private Date creationDate;

    public Double getValueAsDouble() {
        return Double.parseDouble(value);
    }
    public Double getTermAsDouble() {
        return Double.parseDouble(term);
    }
}