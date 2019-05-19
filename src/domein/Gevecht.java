package domein;

import static domein.DomeinController.labels;
import java.util.ArrayList;
import java.util.List;

public class Gevecht {

    private boolean help;
    private List<Monster> monsters = new ArrayList<>();
    private List<Speler> spelersTegen = new ArrayList<>();
    private List<Speler> spelersVoor = new ArrayList<>();
    private Spel spel;
    private int monsterBonus;
    private int spelerzijdeBonus;

    public Gevecht() {
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(int help) {
        if (help == 1 || help == 2) {
            this.help = help == 1;
        } else {
            throw new IllegalArgumentException("1 of 2");
        }
    }

    public List<Monster> getMonster() {
        return monsters;
    }

    public void setMonster(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Speler> getSpelersTegen() {
        return spelersTegen;
    }

    public void setSpelersTegen(List<Speler> spelersTegen) {
        this.spelersTegen = spelersTegen;
    }

    public List<Speler> getSpelersVoor() {
        return spelersVoor;
    }

    public void setSpelersVoor(List<Speler> spelersVoor) {
        this.spelersVoor = spelersVoor;
    }

    public void addSpelerTegen(Speler spelerMaaktKeuze) {
        spelersTegen.add(spelerMaaktKeuze);
    }

    public void addSpelerVoor(Speler spelerMaaktKeuze) {
        spelersVoor.add(spelerMaaktKeuze);
    }

//    public String toonGevechtNiveaus() {
//        int totaalLevel = 0;
//        for (Monster monster : monsters) {
//            totaalLevel += monster.getLevel();
//        }
//        return String.format("%d", totaalLevel);
//    }
    public int berekenMonsterKant() {  // monsterlevel + monsterBonus
        int totaalLevel = 0;
        for (Monster monster : monsters) {
            totaalLevel += monster.getLevel();
        }
        return totaalLevel + monsterBonus-rasMonster();
    }

    public int berekenSpelerKant() {  // (spelerslevel + equipment)van alle spelers die meewerken + SpelerZijdeBonus
        int gevechtsNiveau = 0;
        for (Speler speler : spelersVoor) {
            gevechtsNiveau += (speler.getNiveau() + spel.equipmentBonus(speler) + ras(speler));
        }
        gevechtsNiveau += spelerzijdeBonus;
        return gevechtsNiveau;
    }

    public void addSpelerAanBeurt(Speler spelerAanBeurt) {
        spelersVoor.add(spelerAanBeurt);
    }

    public Spel getSpel() {
        return spel;

    }

    public void setSpel(Spel spel) {
        this.spel = spel;
    }

    public int getMonsterBonus() {
        return monsterBonus;
    }

    public void setMonsterBonus(int monsterBonus) {
        this.monsterBonus = monsterBonus;
    }

    public int getSpelerzijdeBonus() {
        return spelerzijdeBonus;
    }

    public void setSpelerzijdeBonus(int spelerzijdeBonus) {
        this.spelerzijdeBonus = spelerzijdeBonus;
    }

    public boolean helptInGevecht(Speler speler) {
        for (Speler s : spelersVoor) {
            if (s.getNaam().equals(speler.getNaam())) {
                return true;
            }
        }
        return false;
    }

    public void legTConsumableVoor(Kaart kaart) {
        setSpelerzijdeBonus(spelerzijdeBonus + kaart.getStrengthBonus());
    }

    public void legDConsumable(Kaart kaart) {
        setMonsterBonus(monsterBonus + kaart.getMonsterBonus());
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void legTConsumableTegen(Kaart kaart) {
        setMonsterBonus(monsterBonus + kaart.getStrengthBonus());
    }

    public String geefMonsters() {
        String output = "";
        for (Monster monster : monsters) {
            output += String.format("%s%n%n", monster.toString());
        }
        return output;
    }

    public String wieHelpt(Speler speler) {
        for (Speler s : spelersVoor) {
            if (speler.equals(s)) {
                return (labels.getString("helpt"));
            } else {
                return (labels.getString("helpt_niet"));
            }
        }
        return "lol";
    }

    public String uitkomstGevecht() {
        if (monsterBonus < spelerzijdeBonus) {
            return labels.getString("spelers_winnen");
        } else {
            return labels.getString("monsters_winnen");
        }
    }

    public void afloopGevecht() {
        if (monsterBonus < spelerzijdeBonus) {
            gewonnen();
        } else {
            verloren();
        }

    }

    private void gewonnen() {
        int teller = 0;
        for (Speler s : spelersVoor) {
            int count = 0;
            while (count < monsters.size()) {
                s.levelUp();
                count++;
            }
            if (teller < aantalT()) {
                s.addKaartenInHand(spel.geefBovensteSchatKaart());
                teller++;
            }

        }

    }

    private void verloren() {
        for (Speler s : spelersVoor) {
            s.loseLevels(badStuff());
        }
    }

    private int aantalT() {
        int totaal = 0;
        for (Monster m : monsters) {
            totaal += m.getTreasures();
        }
        return totaal;
    }

    private int badStuff() {
        int totaal = 0;
        for (Monster m : monsters) {
            totaal += m.getBadStuff();
        }
        return totaal;
    }

    public void verwijderMonster() {
        monsters.clear();

    }

    private int ras(Speler speler) {
        if ("Dwarf".equals(speler.getNaam())) {
            return 2;
        } else {
            return 0;
        }
    }

    private int rasMonster() {
        int totaal = 0;
        for(Speler s: spelersVoor){
           if( "Halfling".equals(s.getRas())){
               totaal +=2;
           }
        }return totaal;
            
    }
}
