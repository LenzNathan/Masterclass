import org.junit.jupiter.api.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
class Testing {
    private static String[][] addExamples() {
        return new String[][]{
                {"a", "a"},
                {"a", "a"},
                {"a", "a"},
        };
    }

    @ParameterizedTest
    @MethodSource(value = "addExamples")
    void add(String[] row) {
        String a = row[0];
        System.out.println(a);
        String b = row[1];
        System.out.println(b);
        assertEquals(a, b);

    }

    @Test
    void string() {
        assertEquals("a", "a");
    }
}
