package com.codurance;

import java.math.BigDecimal;
import java.util.Objects;

public class BasketItem {
    private final ProductId productId;

    private final int quantity;

    private BigDecimal price;

    public BasketItem(ProductId productId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasketItem{");
        sb.append("productId=").append(productId);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
