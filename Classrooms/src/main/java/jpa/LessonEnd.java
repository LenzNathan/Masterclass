package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.persistence.Entity;
import java.sql.Time;

@Entity
public class LessonEnd extends PanacheEntity {
    private int lessonNumber;
    @JsonbTypeAdapter(SqlTimeAdapter.class)
    private Time time;

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}