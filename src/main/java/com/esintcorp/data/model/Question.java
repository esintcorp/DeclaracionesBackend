package com.esintcorp.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Question")
@EqualsAndHashCode(callSuper=true)
@Data
public class Question extends AuditModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
        name = "question_generator",
        sequenceName = "question_seq",
        initialValue = 1,
        allocationSize = 1
    )
    private Long id;

    @ManyToOne
    private Declaration declaration;

    @ManyToOne
    private BillType billType;

    @OneToMany(mappedBy="question")
    private List<QuestionOption> options;
    
    @NotBlank
    @Size(min = 1, max = 1024)
    private String name;

    @NotNull
    private Integer sequence;

    @NotBlank
    @Size(min = 1, max = 24)
    private String datatype;
}
