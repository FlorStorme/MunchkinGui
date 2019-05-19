package domein;

import static domein.DomeinController.labels;

public class Race extends KerkerKaart {

    private String bonusOmschrijving;

    public Race(int CardID, String bonusOmschrijving, String naam) {
        super.setCardID(CardID);
        setBonusOmschrijving(bonusOmschrijving);
        super.setNaam(naam);
    }


    public String getBonusOmschrijving() {
        return bonusOmschrijving;
    }


    public final void setBonusOmschrijving(String bonusOmschrijving) {
        this.bonusOmschrijving = bonusOmschrijving;
    }

    @Override
    public String toString() {
        String output = "";
        //naam
        output += String.format("%s%n--------%n%s", labels.getString("race"), super.toString());
        //bonusOmschrijving
        output += String.format("%s: %s%n", labels.getString("bonus_omschrijving"), bonusOmschrijving);
        return output;
    }
    
    

}
