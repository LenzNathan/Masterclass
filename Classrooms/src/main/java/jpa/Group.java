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
    private String kennung; //standardmäßig nur ein character
    @ManyToOne
    private Schulklasse schulklasse;
    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public String getKennung() {
        return kennung;
    }

    public void setKennung(String kennung) {
        this.kennung = kennung;
    }

    public Schulklasse getSchulklasse() {
        return schulklasse;
    }

    public void setSchulklasse(Schulklasse schulklasse) {
        this.schulklasse = schulklasse;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
