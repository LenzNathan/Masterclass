package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Room extends PanacheEntity {
    public int number;
    public String floor; // U, E, 0 bis 4
    public int capacity;
    @ManyToOne
    public Building building;
    @ManyToMany
    public List<Tag> tags;
}
