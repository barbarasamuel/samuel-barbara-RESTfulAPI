package com.nnk.springboot.domain;

/*import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;*/

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * To manipulate data about ratings
 *
 */
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable=false)
    private Integer id;

    @NotBlank(message = "The moodys rating is mandatory")
    private String moodysRating;

    @NotBlank(message = "The sand P rating is mandatory")
    private String sandPRating;

    @NotBlank(message = "The fitch rating is mandatory")
    private String fitchRating;

    @NotNull(message = "Must not be null")
    private Integer orderNumber;
}
