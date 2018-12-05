package com.smalik.mongoexample;

import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Objects;

public class NeighborhoodHouseId {

    private String street;
    private String number;

    @PersistenceConstructor
    public NeighborhoodHouseId(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighborhoodHouseId that = (NeighborhoodHouseId) o;
        return street.equals(that.street) &&
                number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }
}
