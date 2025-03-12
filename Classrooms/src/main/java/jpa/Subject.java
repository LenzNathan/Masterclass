package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Subject extends PanacheEntity {
    public String name;
    public int hoursPerWeek;
    public int maxHoursPerDay;
    public int minHoursPerDay; //if the subject is taught on this day, it must be at least this amount of hours
    @ManyToMany
    public List<Tag> tagsUseful;
    @ManyToMany
    public List<Tag> tagsRequired;
}
