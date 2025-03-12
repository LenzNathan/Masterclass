package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Schulklasse extends PanacheEntity {
    @ManyToOne
    public Abteilung abteilung;
    @ManyToOne
    public Jahrgang jahrgang;
    public String kennung; // A, B, C, D, E, ...
    public String nickname; // 2AHWII, 3BFET, ...
    @OneToMany(mappedBy = "schulklasse")
    public List<Student> students;
    @OneToMany(mappedBy = "schulklasse")
    public List<Group> groups;
}
