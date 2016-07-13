package com.codurance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {
    private final String creationDate;
    private List<BasketItem> basketItems = new ArrayList<>();

    public Basket(String creationDate) {
        this.creationDate = creationDate;
    }

    public void add(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(creationDate, basket.creationDate) &&
                Objects.equals(basketItems, basket.basketItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, basketItems);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Basket{");
        sb.append("creationDate='").append(creationDate).append('\'');
        sb.append(", basketItems=").append(basketItems);
        sb.append('}');
        return sb.toString();
    }
}
