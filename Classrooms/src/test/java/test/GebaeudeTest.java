package test;

import jpa.Gebaeude;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GebaeudeTest {

    @Test
    void testInitBuildings() {
        Gebaeude.resetGebaeude();
        Gebaeude.initBuildings();
        assertFalse(Gebaeude.listAll().isEmpty());
        assertEquals(5, Gebaeude.listAll().size());
    }

    @Test
    void getKuerzel() {
        Gebaeude.resetGebaeude();
        Gebaeude g = new Gebaeude("T", "Testgebäude");
        assertEquals("T", g.getKuerzel());
    }

    @Test
    void persist() {
        Gebaeude.resetGebaeude();
        Gebaeude g = new Gebaeude("T", "Testgebäude");
        g.persist();
        assertSame(g, Gebaeude.listAll().get(0));
    }

    @Test
    void getId() {
        Gebaeude.resetGebaeude();
        Gebaeude g = new Gebaeude("T", "Testgebäude");
        assertEquals(0, g.getId());
    }

    @Test
    void findById() {
        Gebaeude.resetGebaeude();
        Gebaeude g = new Gebaeude("T", "Testgebäude");
        g.persist();
        assertEquals(g, Gebaeude.findById(0));
    }

    @Test
    void delete() {
        Gebaeude.resetGebaeude();
        Gebaeude g = new Gebaeude("T", "Testgebäude");
        g.persist();
        Gebaeude.delete(g);
        assertTrue(Gebaeude.listAll().isEmpty());
        g.persist();
        Gebaeude.delete(0);
        assertTrue(Gebaeude.listAll().isEmpty());
    }

    @Test
    void equals() {
        Gebaeude a = new Gebaeude("T", "Testgebäude");
        Gebaeude b = new Gebaeude("T", "Testgebäude");
        assertTrue(a.equals(b));
    }
}
