package com.odebar.webprovider.repository.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<T> implements Serializable {

    T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("%s [id = %s]", getClass().getSimpleName(), id);
    }
}
