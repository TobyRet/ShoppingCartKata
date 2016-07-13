package com.codurance;

import java.util.Objects;

public class UserId {
    private int id;

    public UserId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return id == userId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserId{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
