package com.codurance;

import java.util.Objects;

public class BasketItem {
    private final ProductId productId;
    private final int quantity;

    public BasketItem(ProductId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasketItem{");
        sb.append("productId=").append(productId);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
