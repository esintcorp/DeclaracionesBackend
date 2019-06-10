package com.esintcorp.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Declaration")
@EqualsAndHashCode(callSuper=true)
@Data
public class Declaration extends AuditModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "declaration_generator")
    @SequenceGenerator(
        name = "declaration_generator",
        sequenceName = "declaration_seq",
        initialValue = 1,
        allocationSize = 1
    )
    private Long id;

    @Size(min = 1, max = 16)
    private String type;

    @Size(min = 3, max = 16)
    private String codeSRI;

    @Size(min = 1, max = 128)
    private String name;

    @Size(min = 1, max = 1024)
    private String description;

    @Size(min = 1, max = 16)
    private String cronology;
}
