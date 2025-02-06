package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * To manipulate data about curvePoints
 *
 */
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

    private Integer curveId;
    private Date asOfDate;
    private Double term;
    private Double value;
    private Date creationDate;

    public Double getValueAsDouble(String stringValue) {
        return Double.parseDouble(stringValue);
    }
    public Double getTermAsDouble(String stringTerm) {
        return Double.parseDouble(stringTerm);
    }

    public String getValueAsString(Double doubleValue) { return String.valueOf(doubleValue);}

    public String getTermAsString(Double doubleTerm) { return String.valueOf(doubleTerm);}

}