package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Schulklasse extends PanacheEntity {
    private String kennung; // A, B, C, D, E, ...
    private String nickname; // 2AHWII, 3BFET, ...
    @ManyToOne
    private Abteilung abteilung;
    @ManyToOne
    private Jahrgang jahrgang;
    @OneToMany(mappedBy = "schulklasse")
    private List<Student> students;
    @OneToMany(mappedBy = "schulklasse")
    private List<Group> groups;

    public String getKennung() {
        return kennung;
    }

    public void setKennung(String kennung) {
        this.kennung = kennung;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    public Jahrgang getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(Jahrgang jahrgang) {
        this.jahrgang = jahrgang;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
