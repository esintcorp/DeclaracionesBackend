package com.esintcorp.data.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "SubscriptionPeriod")
@EqualsAndHashCode(callSuper=true)
@Data
public class SubscriptionPeriod extends AuditModel {

	@Id
    @GeneratedValue(generator = "subscription_period_generator")
    @SequenceGenerator(
    	name = "subscription_period_generator",
    	sequenceName = "subscriptionperiod_seq",
    	initialValue = 1,
        allocationSize = 1
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String status;

    @NotBlank
    @Column(columnDefinition = "text")
    private String period;

    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    @Column
    private BigDecimal cost;

    @NotNull
    @Column(columnDefinition = "text")
    private String code;

}
