package com.shuplat.spring.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "gym", schema = "mydb")
public final class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "gym", fetch = FetchType.EAGER)
    private Set<Orders> orders;

    public Gym(final Integer id, final String location) {
        this.id = id;
        this.location = location;
    }

    public Gym() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return id + " " + location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return Objects.equals(id, gym.id) &&
                Objects.equals(location, gym.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}

