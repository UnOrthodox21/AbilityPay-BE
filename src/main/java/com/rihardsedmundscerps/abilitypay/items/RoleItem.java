package com.rihardsedmundscerps.abilitypay.items;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@JsonDeserialize(builder = RoleItem.Builder.class)
public class RoleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    private RoleItem(RoleItem.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleItem roleItem = (RoleItem) o;
        return id == roleItem.id && Objects.equals(name, roleItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "RoleItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private long id;
        private String name;

        public RoleItem.Builder id(long id) {
            this.id = id;
            return this;
        }

        public RoleItem.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RoleItem build() {
            return new RoleItem(this);
        }

    }

}
