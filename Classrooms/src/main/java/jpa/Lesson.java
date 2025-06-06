package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Lesson extends PanacheEntity {
    private int weekdayIndex; //1-5 mo-fr
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Room room;
    @ManyToMany
    private List<Group> groups;
    @ManyToOne
    private LessonBegin begin;
    @ManyToOne
    private LessonEnd end;

    public int getWeekdayIndex() {
        return weekdayIndex;
    }

    public void setWeekdayIndex(int weekdayIndex) {
        this.weekdayIndex = weekdayIndex;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public LessonBegin getBegin() {
        return begin;
    }

    public void setBegin(LessonBegin begin) {
        this.begin = begin;
    }

    public LessonEnd getEnd() {
        return end;
    }

    public void setEnd(LessonEnd end) {
        this.end = end;
    }
}
