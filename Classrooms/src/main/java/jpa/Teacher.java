package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Teacher extends PanacheEntity {
    public String nickname; // 5 characters, first and last are Upper Case
    public String firstname;
    public String lastname;
    @ManyToMany
    public List<Tag> tags;
}
