public enum Gebaeude {
    Stoeckelgebaeude("S"),
    Innrain("I"),
    Hofueberbau("H"),
    Anichstrasse("A"),
    undefined("_");

    private String kuerzel;

    Gebaeude(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public static Gebaeude fromKuerzel(String k) {
        switch (k) {
            case "S":
                return Stoeckelgebaeude;
            case "I":
                return Gebaeude.Innrain;
            case "H":
                return Gebaeude.Hofueberbau;
            case "A":
                return Gebaeude.Anichstrasse;
            default:
                throw new RuntimeException("Kuerzel beginnt mit keiner gültigen Gebaeudebezeichnung");
        }
    }
}
