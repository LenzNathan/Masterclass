package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Jahrgang extends PanacheEntity {
    private int stufe;
    @ManyToOne
    private Abteilung abteilung;
    @ManyToMany
    private List<Tag> tags;

    public int getStufe() {
        return stufe;
    }

    public void setStufe(int stufe) {
        this.stufe = stufe;
    }

    public Abteilung getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(Abteilung abteilung) {
        this.abteilung = abteilung;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
