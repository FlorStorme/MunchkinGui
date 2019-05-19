package domein;

import static domein.DomeinController.labels;

public class DConsumable extends KerkerKaart {

    private int monsterBonus;

    public DConsumable(int CardID, int monsterBonus, String naam) {
        super.setCardID(CardID);
        setMonsterBonus(monsterBonus);
        super.setNaam(naam);
    }

    

    public final void setMonsterBonus(int monsterBonus) {
        this.monsterBonus = monsterBonus;
    }

    

    public int getMonsterBonus() {
        return monsterBonus;
    }

    @Override
    public String toString() {
        String output = "";
        //naam
        output += String.format("%s%n--------%n%s", labels.getString("consumable"),super.toString());
        //monsterBonus
        output += String.format("%s: %s%n", labels.getString("monster_bonus"), monsterBonus);
        return output;
    }
    
    

}
