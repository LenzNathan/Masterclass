package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Building extends PanacheEntity {
    public String kuerzel; //standardmäßig nur ein character
    public String name;
}
