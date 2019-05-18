package domein;

import static cui.UC1_MaakSpel.labels;

public class TConsumable extends SchatKaart {

    private int strengthBonus;
    private int isLevelUp;

    public TConsumable(int CardID, int strengthBonus, int isLevelUp, int verkoopPrijs, String naam) {
        super.setCardID(CardID);
        setStrengthBonus(strengthBonus);
        setIsLevelUp(isLevelUp);
        super.setVerkoopPrijs(verkoopPrijs);
        super.setNaam(naam);

    }


    @Override
    public int getStrengthBonus() {
        return strengthBonus;
    }

    @Override
    public int getIsLevelUp() {
        return isLevelUp;
    }

    public final void setStrengthBonus(int strenghtBonus) {
        this.strengthBonus = strenghtBonus;
    }

    public final void setIsLevelUp(int isLevelUp) {
        this.isLevelUp = isLevelUp;
    }

    @Override
    public String toString() {
        String output = "";
        //naam + verkoopPrijs
        output += String.format("%s%n--------%n%s", labels.getString("consumable"),super.toString());
        //strengthBonus of levelUp
        if (strengthBonus != 0) {
            output += String.format("%s: %s%n", labels.getString("strength_bonus"), strengthBonus);
        } else {
            output += String.format("%s: %s%n", labels.getString("levels_gained"), isLevelUp);
        }
        return output;
    }

}
