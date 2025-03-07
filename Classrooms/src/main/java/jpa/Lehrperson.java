package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Lehrperson extends PanacheEntity {
    private String kuerzel;
    private List<Taetigkeit> taetigkeiten;
}
