package com.codurance;

import java.math.BigDecimal;

public class PriceService {
    public BigDecimal priceFor(ProductId productId) {
        return productId.getId() == 1 ? new BigDecimal("5.00") : new BigDecimal("7.00");
    }
}
