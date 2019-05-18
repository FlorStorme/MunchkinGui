package domein;

import static cui.UC1_MaakSpel.labels;

public class Equipment extends SchatKaart {

    private String equipSlot;
    private String specialBonus;
    private int strengthBonus;
    private String usableBy;

    public Equipment(int CardID, String equipSlot, String specialBonus, int strengthBonus, String usableBy, String naam, int verkoopPrijs) {
        super.setCardID(CardID);
        setEquipSlot(equipSlot);
        setSpecialBonus(specialBonus);
        setStrengthBonus(strengthBonus);
        setUsableBy(usableBy);
        super.setNaam(naam);
        super.setVerkoopPrijs(verkoopPrijs);

    }


    public final void setEquipSlot(String equipSlot) {
        this.equipSlot = equipSlot;
    }

    public final void setSpecialBonus(String specialBonus) {
        this.specialBonus = specialBonus;
    }


    public String getEquipSlot() {
        return equipSlot;
    }

    public String getSpecialBonus() {
        return specialBonus;
    }

    @Override
    public int getStrengthBonus() {
        return strengthBonus;
    }

    public String getUsableBy() {
        return usableBy;
    }

    public final void setStrengthBonus(int strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    public final void setUsableBy(String usableBy) {
        this.usableBy = usableBy;
    }

    @Override
    public String toString() {
        String output = "";
        //naam + verkoopPrijs
        output += String.format("%s%n--------%n%s", labels.getString("equipment"), super.toString());
        //equipSlot
        output += String.format("%s: %s%n", labels.getString("equip_slot"), equipSlot);
        //specialBonus
        if (specialBonus != null) {
            output += String.format("%s: %s%n", labels.getString("special_bonus"), specialBonus);
        }
        //strengthBonus
        output += String.format("%s: %s%n", labels.getString("strength_bonus"), strengthBonus);
        //usableBy
        output += String.format("%s: %s%n", labels.getString("usable_by"), usableBy);
        return output;
    }

}
