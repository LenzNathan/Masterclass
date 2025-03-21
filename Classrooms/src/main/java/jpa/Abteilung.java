package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Abteilung extends PanacheEntity {
    public String nickname; //one character representation of name
    public String name;
}