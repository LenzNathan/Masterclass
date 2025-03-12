package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

@Entity
public class Lesson extends PanacheEntity {
    public Date date;
    @ManyToOne
    public Subject subject;
    @ManyToOne
    public Room room;
    @ManyToMany
    public List<Group> groups;
    @ManyToOne
    public LessonBegin begin;
    @ManyToOne
    public LessonEnd end;
}
