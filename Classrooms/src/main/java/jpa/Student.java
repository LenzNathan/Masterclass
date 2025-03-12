package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Student extends PanacheEntity {
    public String firstname;
    public String lastname;
    @ManyToOne
    public Schulklasse schulklasse;
    @ManyToOne
    public Group group;
}
