package com.esintcorp.data.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BillResume {
    private BigDecimal buyTotal;
    private BigDecimal sellTotal;
    private BigDecimal paymentTotal;
}
