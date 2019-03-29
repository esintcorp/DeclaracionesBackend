package com.esintcorp.data.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PaymentCard")
@EqualsAndHashCode(callSuper=true)
@Data
public class PaymentCard extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "paymentcard_generator")
    @SequenceGenerator(
            name = "paymentcard_generator",
            sequenceName = "paymentcard_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String status;

    @Size(min = 3, max = 1024)
    private String cardName;

    @Column(unique=true)
    @Size(min = 10, max = 20)
    private String cardNumber;

    @Size(min = 3, max = 8)
    private String cvv;

    @NotNull
    private LocalDate expirationDate;
    
    @OneToOne
    private User user;
}
