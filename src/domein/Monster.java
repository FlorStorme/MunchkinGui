package domein;

import static cui.UC1_MaakSpel.labels;

public class Monster extends KerkerKaart {

    private int badStuff;
    private String bonusAgainstRace;
    private int levelsGained;
    private int treasures;
    private int bonusAgainstRun;
    private int level;

    public Monster(int CardID, int badStuff, String bonusAgainstRace, int levelsGained, int treasures, int bonusAgainstRun, String naam, int level) {
        super.setCardID(CardID);
        setBadStuff(badStuff);
        setBonusAgainstRace(bonusAgainstRace);
        setLevelsGained(levelsGained);
        setTreasures(treasures);
        setBonusAgainstRun(bonusAgainstRun);
        super.setNaam(naam);
        setLevel(level);
    }


    public int getBadStuff() {
        return badStuff;
    }

    public String getBonusAgainstRace() {
        return bonusAgainstRace;
    }

    public int getLevelsGained() {
        return levelsGained;
    }

    public int getTreasures() {
        return treasures;
    }

    public int getBonusAgainstRun() {
        return bonusAgainstRun;
    }

    public int getLevel() {
        return level;
    }

    public final void setBadStuff(int badStuff) {
        this.badStuff = badStuff;
    }

    public final void setBonusAgainstRace(String bonusAgainstRace) {
        this.bonusAgainstRace = bonusAgainstRace;
    }

    public final void setLevelsGained(int levelsGained) {
        this.levelsGained = levelsGained;
    }

    public final void setTreasures(int treasures) {
        this.treasures = treasures;
    }

    public final void setBonusAgainstRun(int bonusAgainstRun) {
        this.bonusAgainstRun = bonusAgainstRun;
    }

    public final void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String output = "";
        //naam
        output += String.format("Monster%n--------%n%s", super.toString());
        //level
        output += String.format("%s: %s%n", labels.getString("level"), level);
        //badstuff
        output += String.format("%s: %d%n", labels.getString("badstuff"), badStuff);
        //treasures
        output += String.format("%s: %s%n", labels.getString("treasures"), treasures);
        //levelsGained
        output += String.format("%s: %s%n", labels.getString("levels_gained"), levelsGained);
        //bonusAgainstRace
        if(bonusAgainstRace != null){
            output += String.format("%s: %s%n", labels.getString("bonus_against_race"), bonusAgainstRace);
        }
        //bonusAgainstRun
        if(bonusAgainstRun != 0){
            output += String.format("%s: %s%n", labels.getString("bonus_against_run"), bonusAgainstRun);
        }
        return output;
    }
    
    

}
