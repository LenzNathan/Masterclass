package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity(name="Classpart")
@Table(name = "classpart")
public class Group extends PanacheEntity {
    public String kennung; //standardmäßig nur ein character
    @ManyToOne
    public Schulklasse schulklasse;
    @OneToMany(mappedBy = "group")
    public List<Student> students;
}
