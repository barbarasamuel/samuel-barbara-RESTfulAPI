package com.nnk.springboot.domain;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false)
    private Integer Id;

    private Integer CurveId;
    private Date asOfDate;
    private Double term;
    private Double value;
    private Date creationDate;
}
