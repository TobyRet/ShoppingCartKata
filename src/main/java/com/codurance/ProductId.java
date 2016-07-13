package com.codurance;

import java.util.Objects;

public class ProductId {
    private int id;

    public ProductId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return id == productId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProductId{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
