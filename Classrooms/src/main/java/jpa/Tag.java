package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Tag extends PanacheEntity {
    private String name;
    public String getName() {
        return name;
    }
}
