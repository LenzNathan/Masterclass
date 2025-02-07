package jpa;

import java.util.ArrayList;

public class Gebaeude {
    public static void initBuildings() {
        Gebaeude undefined = new Gebaeude("_", "undefined");
        Gebaeude Stoeckelgebaeude = new Gebaeude("S", "Stoeckelgebaeude");
        Gebaeude Innrain = new Gebaeude("I", "Innrain");
        Gebaeude Hofueberbau = new Gebaeude("H", "Hofueberbau");
        Gebaeude Anichstrasse = new Gebaeude("A", "Anichstrasse");

        undefined.persist();
        Stoeckelgebaeude.persist();
        Innrain.persist();
        Hofueberbau.persist();
        Anichstrasse.persist();
    }

    private final String kuerzel;
    String name;
    private final int id;

    private static ArrayList<Gebaeude> buildings = new ArrayList<Gebaeude>();

    public Gebaeude(String kuerzel, String name) {
        this.kuerzel = kuerzel;
        this.name = name;

        int size = buildings.size();
        int id = size;
        for (Gebaeude g : buildings) {
            if (g.id == size) {
                id += 1;
                break;
            }
        }
        this.id = id;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void persist() {
        if (!buildings.contains(this)) {
            buildings.add(this);
        }
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Gebaeude> listAll() {
        return buildings;
    }


    public static Gebaeude findById(int id) {
        for (Gebaeude g : buildings) {
            if (g.id == id) {
                return g;
            }
        }
        return null;
    }

    public static void delete(Gebaeude g) {
        buildings.remove(g);
    }

    public static void delete(int id) {
        buildings.removeIf(g -> g.id == id);
    }

    public static void resetGebaeude() {
        buildings.clear();
    }

    public boolean equals(Gebaeude g) {
        return g.getKuerzel().equals(getKuerzel()) && g.name.equals(name);
    }

    public static Gebaeude fromKuerzel(String identifier) {
        for (Gebaeude g : buildings) {
            if (g.getKuerzel().equals(identifier)) {
                return g;
            }
        }
        return null;
    }
}
