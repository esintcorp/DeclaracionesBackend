package com.esintcorp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "subscription")
@EqualsAndHashCode(callSuper=true)
@Data
public class Subscription extends AuditModel {

	@Id
    @GeneratedValue(generator = "subscription_generator")
    @SequenceGenerator(
            name = "subscription_generator",
            sequenceName = "subscription_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String status;

    @Size(min = 3, max = 15)
    private String type;

    @OneToOne
    private User user;

    @OneToOne
    private SubscriptionPeriod subscriptionPeriod;
}
