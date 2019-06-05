package com.esintcorp.data.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Bill")
@EqualsAndHashCode(callSuper=true)
@Data
public class Bill extends AuditModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "bill_generator")
    @SequenceGenerator(
            name = "bill_generator",
            sequenceName = "bill_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @NotNull
    @Column
    private BigDecimal subtotal;

    @NotNull
    @Column
    private BigDecimal iva;

    @NotNull
    @Column
    private BigDecimal total;

    @ManyToOne
    private User user;
}
