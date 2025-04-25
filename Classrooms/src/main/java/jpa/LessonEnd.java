package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.sql.Time;

@Entity
public class LessonEnd extends PanacheEntity {
    private int lessonNumber;
    private Time time;

    public static int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public static Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}