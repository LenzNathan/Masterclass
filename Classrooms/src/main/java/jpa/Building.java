package jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Building extends PanacheEntity {
    public long id;
    public String kuerzel; //standardmäßig nur ein character
    public String name;

    public static Building getBuildingById(long id) {
        return find("id", id).firstResult();
    }
    public static Building getBuildingByKuerzel(String kuerzel) {
        return find("kuerzel", kuerzel).firstResult();
    }
    public static List<Building> getAllBuildings() {
        return listAll();
    }
}
