package com.smalik.mongoexample;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@CompoundIndexes({
        @CompoundIndex(name = "age_idx", def = "{'street': 1, 'age': -1}")
})
public class NeighborhoodHouse {

    @Id
    private NeighborhoodHouseId id;
    @Indexed
    private boolean active;
    private int age;
    private Map<String, String> attributes;

    public NeighborhoodHouseId getId() {
        return id;
    }

    public void setId(NeighborhoodHouseId id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return id.getNumber();
    }

    public String getStreet() {
        return id.getStreet();
    }
}
