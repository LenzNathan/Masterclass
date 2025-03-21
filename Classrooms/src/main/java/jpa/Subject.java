package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Subject extends PanacheEntity {
    private String name;
    private int hoursPerWeek;
    private int maxHoursPerDay;
    private int minHoursPerDay; //if the subject is taught on this day, it must be at least this amount of hours
    @ManyToMany
    private List<Tag> tagsUseful;
    @ManyToMany
    private List<Tag> tagsRequired;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getMaxHoursPerDay() {
        return maxHoursPerDay;
    }

    public void setMaxHoursPerDay(int maxHoursPerDay) {
        this.maxHoursPerDay = maxHoursPerDay;
    }

    public int getMinHoursPerDay() {
        return minHoursPerDay;
    }

    public void setMinHoursPerDay(int minHoursPerDay) {
        this.minHoursPerDay = minHoursPerDay;
    }

    public List<Tag> getTagsUseful() {
        return tagsUseful;
    }

    public void setTagsUseful(List<Tag> tagsUseful) {
        this.tagsUseful = tagsUseful;
    }

    public List<Tag> getTagsRequired() {
        return tagsRequired;
    }

    public void setTagsRequired(List<Tag> tagsRequired) {
        this.tagsRequired = tagsRequired;
    }
}
