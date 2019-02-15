package com.esintcorp.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "systemUser")
@EqualsAndHashCode(callSuper=true)
@Data
public class User extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String status;

    @NotBlank
    @Size(min = 3, max = 15)
    private String type;

    @NotNull
    private Long idCard;

    @NotNull
    private Long rucNumber;

    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String email;


}
