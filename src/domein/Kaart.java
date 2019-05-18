package domein;

import static cui.UC1_MaakSpel.labels;


public class Kaart {

    private String naam;
    private int CardID;

    public int getCardID() {
        return CardID;
    }

    public void setCardID(int CardID) {
        this.CardID = CardID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n", labels.getString("naam"), naam);
    }

    public int getLevelMin(){
        return 0;
    }
    
    public int getStrengthBonus(){  
        return 0;
    }
    
    public boolean isKaartEquipment(Kaart kaart) {
        boolean check = false;
        if ("Equipment".equals(kaart.getClass().getSimpleName())) {
            check = true;
        }
        return check;
    }

    public int getIsLevelUp(){
        return 0;
    }
    
    public int getMonsterBonus(){
        return 0;
    }

    public String getEquipSlot(){
        return null; 
    }

    public boolean isKaartRace(Kaart k) {
        boolean check = false;
        if ("Race".equals(k.getClass().getSimpleName())) {
            check = true;
        }
        return check;
    }

    public int getVerkoopPrijs() {
        return 0;
    }
    
    
}
