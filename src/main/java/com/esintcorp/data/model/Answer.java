package com.esintcorp.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "Answer")
@EqualsAndHashCode(callSuper=true)
@Data
public class Answer extends AuditModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String value;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bill bill;
}
