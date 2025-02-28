package test;

import jpa.Gebaeude;
import jpa.Raum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaumTest {

    private static Stream<Arguments> data() {
        Gebaeude.resetGebaeude();
        Gebaeude.initBuildings();
        return Stream.of(
                Arguments.of("_U01", Gebaeude.fromKuerzel("_"), 'U', 1),
                Arguments.of("SE65", Gebaeude.fromKuerzel("S"), 'E', 65),
                Arguments.of("I123", Gebaeude.fromKuerzel("I"), '1', 23),
                Arguments.of("H201", Gebaeude.fromKuerzel("H"), '2', 1),
                Arguments.of("A308", Gebaeude.fromKuerzel("A"), '3', 8),
                Arguments.of("I456", Gebaeude.fromKuerzel("I"), '4', 56)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void fromKuerzel(String kuerzel, Gebaeude g, char geschoss, int nummer) {
        Raum r = Raum.fromKuerzel(kuerzel);
        assertEquals(g, r.getGebaeude());
        assertEquals(geschoss, r.getGeschoss());
        assertEquals(nummer, r.getNummer());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    void newRaumAndKuerzel(String wantKuerzel, Gebaeude g, char geschoss, int nummer) {
        Raum r = new Raum(g, geschoss, nummer);
        assertEquals(geschoss, r.getGeschoss());
        assertEquals(nummer, r.getNummer());
        assertEquals(g, r.getGebaeude());
        assertEquals(wantKuerzel, r.getKuerzel());
    }
}
