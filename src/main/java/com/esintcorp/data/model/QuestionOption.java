package com.esintcorp.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "QuestionOption")
@EqualsAndHashCode(callSuper=true)
@Data
public class QuestionOption extends AuditModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "questionOption_generator")
    @SequenceGenerator(
        name = "questionOption_generator",
        sequenceName = "questionOption_seq",
        initialValue = 1,
        allocationSize = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String value;

    @ManyToOne
    @JsonBackReference
    private Question question;

    @NotNull
    private Integer sequence;
}
