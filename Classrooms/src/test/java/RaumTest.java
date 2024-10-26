import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaumTest {

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(Gebaeude.Anichstrasse, 'U', 15, "AU15"),
                Arguments.of(Gebaeude.Innrain, 'E', 3, "IE03"),
                Arguments.of(Gebaeude.Hofueberbau, '1', 42, "H142"),
                Arguments.of(Gebaeude.Stoeckelgebaeude, '2', 1, "S201"),
                Arguments.of(Gebaeude.Innrain, '3', 22, "I322"),
                Arguments.of(Gebaeude.Anichstrasse, '4', 53, "A453")
        );
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    void fromKuerzel(Gebaeude wantg, char wantGeschoss, int wantNummer, String kuerzel) {
    Raum r = Raum.fromKuerzel(kuerzel);
    assertEquals(wantg, r.getGebaeude());
    assertEquals(wantGeschoss, r.getGeschoss());
    assertEquals(wantNummer, r.getNummer());
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    void newRaumAndKuerzel(Gebaeude g, char geschoss, int nummer, String wantKuerzel) {
        Raum r = new Raum(g, geschoss, nummer);
        assertEquals(geschoss, r.getGeschoss());
        assertEquals(nummer, r.getNummer());
        assertEquals(g, r.getGebaeude());
        assertEquals(wantKuerzel, r.getKuerzel());
    }
}