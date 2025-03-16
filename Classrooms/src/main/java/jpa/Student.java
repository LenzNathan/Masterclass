package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Student extends PanacheEntity {
    private String firstname;
    private String lastname;
    @ManyToOne
    private Schulklasse schulklasse;
    @ManyToOne
    private Group group;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Schulklasse getSchulklasse() {
        return schulklasse;
    }

    public void setSchulklasse(Schulklasse schulklasse) {
        this.schulklasse = schulklasse;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
