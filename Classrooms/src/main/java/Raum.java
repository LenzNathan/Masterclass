
public class Raum {
    private Gebaeude gebaeude = Gebaeude.undefined;
    private char geschoss = '-'; // U,E,1,2,3,4 ..., -
    private int nummer = -1; // sollte 1 bis 2 Stellen haben - beim einlesen aufpassen!
    private String kuerzel = "";


    public Raum() {
    }

    public Raum(Gebaeude gebaeude, char geschoss, int nummer) {
        this.gebaeude = gebaeude;
        this.geschoss = geschoss;
        this.nummer = nummer;
        generateKuerzel();
    }

    private void generateKuerzel() {
        String k = "";
        k = gebaeude.name().substring(0, 1);
        k += geschoss;
        if (nummer % 10 > 1) { // nummer hat nur eine Stelle
            k += "0" + nummer;
        } else if (nummer % 100 > 1) {
            throw new RuntimeException("Raumnummer hat mehr als 2 Stellen - Kürzel kann nicht generiert werden ");
        } else {
            k += nummer;
        }
        kuerzel = k;
        System.out.println(kuerzel);
    }

    private void setFromKuerzel(String k) {
        // Gebaeude setzen
        switch (k.substring(0, 1)) {
            case "S":
                gebaeude = Gebaeude.Stoeckelgebaeude;
                break;
            case "I":
                gebaeude = Gebaeude.Innrain;
                break;
            case "H":
                gebaeude = Gebaeude.Hofueberbau;
                break;
            case "A":
                gebaeude = Gebaeude.Anichstrasse;
            default:
                throw new RuntimeException("Kuerzel beginnt mit keiner gültigen Gebaeudebezeichnung");
        }
        // Geschoss setzen
        switch (k.substring(1, 2)) {
            case "U":
                geschoss = 'U';
                break;
            case "E":
                geschoss = 'E';
                break;
            default:
                try {
                    int s = Integer.parseInt(k.substring(1, 2));
                    geschoss = (char) s;
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Kuerzel hat keine gültige Stockbezeichnung");
                }
        }
        // Raumnummer setzen
        try {
            int r = Integer.parseInt(k.substring(2, k.length() - 1));
            nummer = r;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Kuerzel hat keine gültige Raumbezeichnung");
        }
    }

    public Gebaeude getGebaeude() {
        return gebaeude;
    }

    public void setGebaeude(Gebaeude gebaeude) {
        this.gebaeude = gebaeude;
    }

    public char getGeschoss() {
        return geschoss;
    }

    public void setGeschoss(char geschoss) {
        this.geschoss = geschoss;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        setFromKuerzel(kuerzel);
        this.kuerzel = kuerzel;
    }
}
