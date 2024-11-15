import java.util.List;

public class Werkstaette extends Raum{
    private List<Taetigkeit> taetigkeiten;
    private int Taetigkeitsplaetze = 0;
    public Werkstaette(Gebaeude gebaeude, char geschoss, int nummer){
      super(gebaeude, geschoss, nummer);
    }
}
