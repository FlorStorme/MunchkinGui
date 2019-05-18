package domein;

import static cui.UC1_MaakSpel.labels;

public class Curse extends KerkerKaart {

    private int levelMin;

    public Curse(int CardID, int levelMin, String naam) {
        super.setCardID(CardID);
        setLevelMin(levelMin);
        super.setNaam(naam);
    }

    

    @Override
    public int getLevelMin() {
        return levelMin;
    }

    public final void setLevelMin(int levelMin) {
        this.levelMin = levelMin;
    }

    @Override
    public String toString() {
        String output = "";
        //naam
        output += String.format("%s%n--------%n%s", labels.getString("curse"), super.toString());
        //levelMin
        output += String.format("%s: %s%n", labels.getString("level_min"), levelMin);
        return output;
    }
    
    

}
