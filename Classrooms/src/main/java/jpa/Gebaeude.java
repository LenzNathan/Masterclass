package jpa;

import java.util.ArrayList;

public class Gebaeude implements Resource {
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

    private static ArrayList<Gebaeude> buildings;

    // <editor-fold> Constructors
    Gebaeude(String kuerzel, String name) {
        this.kuerzel = kuerzel;
        this.name = name;

        int size = buildings.size();
        int id = size;
        boolean invalidID = true;
        while (invalidID) {
            for (Gebaeude g : buildings) {
                if (g.id == size) {
                    id += 1;
                    break;
                }
                invalidID = false;
            }
        }
        this.id = id;
    }
    // </editor-fold>

    public String getKuerzel() {
        return kuerzel;
    }

    public void persist() {
        buildings.add(this);
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Gebaeude> listAll() {
        return buildings;
    }

    @Override
    public Gebaeude findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}