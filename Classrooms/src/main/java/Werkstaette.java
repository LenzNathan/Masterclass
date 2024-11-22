import java.util.List;

public class Werkstaette extends Raum{
    private int Taetigkeitsplaetze = 0;
    private List<Taetigkeit> taetigkeiten;
    public Werkstaette(Gebaeude gebaeude, char geschoss, int nummer){
      super(gebaeude, geschoss, nummer);
    }
}
