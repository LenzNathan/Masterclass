package jpa;

import java.util.List;

public class LessonPlan {
    private int id;
    private List<Lesson> lessons;

    public LessonPlan() {
    }

    public LessonPlan(int id, List<Lesson> lessons) {
        this.id = id;
        this.lessons = lessons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
