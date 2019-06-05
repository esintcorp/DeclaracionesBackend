package com.esintcorp.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Subscription")
@EqualsAndHashCode(callSuper=true)
@Data
public class Subscription extends AuditModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "subscription_generator")
    @SequenceGenerator(
            name = "subscription_generator",
            sequenceName = "subscription_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @Size(min = 3, max = 15)
    private String type;

    @OneToOne
    private User user;

    @OneToOne
    private SubscriptionPeriod period;
}
