package test;

import jpa.Gebaeude;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class GebaeudeTest {

    @Test
    void initBuildings() {
        Gebaeude.initBuildings();
        Assert.notEmpty(Gebaeude.listAll());
    }

    @Test
    void getKuerzel() {
    }

    @Test
    void persist() {
    }

    @Test
    void getId() {
    }

    @Test
    void listAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}