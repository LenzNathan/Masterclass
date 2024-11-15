import java.util.ArrayList;
import java.util.List;

public class Raum {
    private int schuelerkapazitaet;

    private Gebaeude gebaeude = Gebaeude.undefined;
    private char geschoss = '-'; // U,E,1,2,3,4 ..., -
    private int nummer = -1; // sollte 1 bis 2 Stellen haben - beim einlesen aufpassen!
    private String kuerzel = "";

    public Raum(Gebaeude gebaeude, char geschoss, int nummer) {
        this.gebaeude = gebaeude;
        this.geschoss = geschoss;
        this.nummer = nummer;
        this.kuerzel = generateKuerzel(gebaeude, geschoss, nummer);
    }

    public static Raum fromKuerzel(String k) {
        // Gebaeude setzen
        var geb = Gebaeude.fromKuerzel(k.substring(0, 1));
        // Geschoss setzen
        char geschoss = k.charAt(1);
        List<Character> geschosse = new ArrayList<>();
        geschosse.add('U');
        geschosse.add('E');
        geschosse.add('1');
        geschosse.add('2');
        geschosse.add('3');
        geschosse.add('4');
        if (!geschosse.contains(geschoss)) {
            throw new RuntimeException("Kuerzel hat keine gültige Stockbezeichnung");
        }
        // Raumnummer setzen
        int n = 0;
        try {
            int r = Integer.parseInt(k.substring(2));
            n = r;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Kuerzel hat keine gültige Raumbezeichnung");
        }
        return new Raum(geb, geschoss, n);
    }

    private String generateKuerzel(Gebaeude gebaeude, char geschoss, int nummer) {
        String k = "";
        k = gebaeude.getKuerzel();
        k += geschoss;
        if (nummer < 10) { // nummer hat nur eine Stelle
            k += "0" + nummer;
        } else if (nummer > 99) {
            throw new RuntimeException("Raumnummer hat mehr als 2 Stellen - Kürzel kann nicht generiert werden ");
        } else {
            k += nummer;
        }
        return k;
    }

    public Gebaeude getGebaeude() {
        return gebaeude;
    }

    public char getGeschoss() {
        return geschoss;
    }

    public int getNummer() {
        return nummer;
    }

    public String getKuerzel() {
        return kuerzel;
    }
}
