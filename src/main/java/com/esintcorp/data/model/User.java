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
@Table(name = "SystemUser")
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
            sequenceName = "systemuser_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    private String type;

    @NotNull
    @Column(unique=true)
    private String idCard;

    @Column(unique=true)
    private String rucNumber;

    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(unique=true)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;


}
