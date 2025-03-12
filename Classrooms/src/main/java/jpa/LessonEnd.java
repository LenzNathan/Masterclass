package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.sql.Time;

@Entity
public class LessonEnd extends PanacheEntity {
    public int lessonNumber;
    public Time time;
}