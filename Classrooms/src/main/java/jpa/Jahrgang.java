package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Jahrgang extends PanacheEntity {
    public int stufe;
    @ManyToOne
    public Abteilung abteilung;
    @ManyToMany
    public List<Tag> tags;
}
