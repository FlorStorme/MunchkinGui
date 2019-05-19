package domein;

import static domein.DomeinController.labels;
import java.util.ArrayList;
import java.util.List;

public class Spel {

    private String naam;
    private final List<SchatKaart> schatKaarten;
    private final List<KerkerKaart> kerkerKaarten;
    private int aantal;
    private final List<Speler> spelers = new ArrayList<>();
    private Speler spelerAanBeurt;
    private Speler spelerMaaktKeuze;
    private Kaart bovensteKaartSpel;
    private Gevecht gevecht;
    
    public Spel(String naam, int aantal){
        setNaam(naam);
        setAantal(aantal);
        schatKaarten = new ArrayList<>();
        kerkerKaarten = new ArrayList<>();
    }

    public Spel(List<SchatKaart> schatKaarten, List<KerkerKaart> kerkerKaarten) {
        //shuffle

        ArrayList<KerkerKaart> temp = new ArrayList<>();
        while (!kerkerKaarten.isEmpty()) {
            int loc = (int) (Math.random() * kerkerKaarten.size());
            temp.add(kerkerKaarten.get(loc));
            kerkerKaarten.remove(loc);
        }
        kerkerKaarten = temp;

        this.kerkerKaarten = kerkerKaarten;
        //shuffle

        ArrayList<SchatKaart> tempstfu = new ArrayList<>();
        while (!schatKaarten.isEmpty()) {
            int loc = (int) (Math.random() * schatKaarten.size());
            tempstfu.add(schatKaarten.get(loc));
            schatKaarten.remove(loc);
        }
        schatKaarten = tempstfu;
        this.schatKaarten = schatKaarten;
    }

    public List<SchatKaart> getSchatKaarten() {
        return schatKaarten;
    }

    public List<KerkerKaart> getKerkerKaarten() {
        return kerkerKaarten;
    }
    
    

    public String getNaam() {
        return naam;
    }

    public final boolean setNaam(String naam) {
        String tempNaam = naam.toLowerCase();
        int teller = 0;
        if (naam.length() < 6 || naam.length() > 12) {
            throw new IllegalArgumentException(labels.getString("naam_verkeerd_lengte"));
        } else {
            for (int i = 0; i < tempNaam.length(); i++) {
                char c = tempNaam.charAt(i);
                if ((c < 'a' || c > 'z') && (c < '0' || c > '9')) {
                    throw new IllegalArgumentException(labels.getString("spel_naam_verkeerd"));
                }

                if ((c >= '0' && c <= '9')) {
                    teller++;
                }
            }
            if (teller >= 3) {

                this.naam = naam;
                return true;
            } else {
                throw new IllegalArgumentException("spel_naam_verkeerd");
            }

        }
    }

    public final void setAantal(int aantal) {
        if (aantal < 3 || aantal > 6) {
            throw new IllegalArgumentException("juist_aantal_spelers");
        }
        this.aantal = aantal;
    }

    public int getAantal() {
        return aantal;
    }

    public Speler getSpelerAanBeurt() {
        return spelerAanBeurt;
    }

    public void setSpelerAanBeurt(Speler spelerAanBeurt) {
        this.spelerAanBeurt = spelerAanBeurt;
        setSpelerMaaktKeuze(spelers.get(geefIndex(spelerAanBeurt) + 1 < aantal ? geefIndex(spelerAanBeurt) + 1 : 0));

    }

    public void volgendeSpeler() {
        if (geefIndex(spelerAanBeurt) + 1 < aantal) {
            setSpelerAanBeurt(spelers.get(geefIndex(spelerAanBeurt) + 1));
        } else {
            setSpelerAanBeurt(spelers.get(0));
        }
    }

    public void voegSpelerToe(Speler s) throws Exception {

        String lNaam = s.getNaam().toLowerCase();
        spelers.stream().filter((speler) -> (lNaam.equals(speler.getNaam().toLowerCase()))).forEachOrdered((_item) -> {
            throw new IllegalArgumentException("naam_bestaat_al");
        });
        List<Kaart> kaarten = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            kaarten.add(geefBovensteKerkerKaart());
            kaarten.add(geefBovensteSchatKaart());
        }
        s.setKaartenInHand(kaarten);
        spelers.add(s);

    }

    public KerkerKaart geefBovensteKerkerKaart() {
        KerkerKaart bovensteKaart = kerkerKaarten.get(0);
        setBovensteKaartSpel(bovensteKaart);
        kerkerKaarten.remove(0);
        return bovensteKaart;
    }

    public SchatKaart geefBovensteSchatKaart() {
        SchatKaart bovensteKaart = schatKaarten.get(0);
        schatKaarten.remove(0);
        return bovensteKaart;
    }

    public String geefDetailSpelersGevecht() {
        String output = "";
        int i = 1;
        for (Speler speler : spelers) {
            output += String.format("%s%n%s", speler.toString(), helptInGevecht(speler) ? "helpt" : "helpt niet");
            i++;
        }
        return output;
    }

    public boolean helptInGevecht(Speler speler) {
        return gevecht.helptInGevecht(speler);
    }

    public String geefDetailSpelers() {
        String output = "";
        int i = 1;
        for (Speler speler : spelers) {
            output += String.format("%nSpeler %d:%n%s", i, speler.toString());
            i++;
        }
        return output;
    }

    public String geefSpelOverzicht() {
        String output = "";
        output = spelers.stream().map((speler) -> String.format("%s:%n%s: %d%n%s: %s%n%s: %s%n", speler.getNaam(), labels.getString("niveau"), speler.getNiveau(), labels.getString("geslacht"), speler.getGeslacht(), "kaarten", (speler.getKaartenOpTafel() == null) ? labels.getString("geen_kaarten_op_tafel") : speler.getKaartenOpTafel().toString())).reduce(output, String::concat);
        return output;
    }

    public Kaart getBovensteKaartSpel() {
        return bovensteKaartSpel;
    }

    public void setBovensteKaartSpel(Kaart bovensteKaartSpel) {
        this.bovensteKaartSpel = bovensteKaartSpel;
    }

    public void eersteSpeler() {
        Speler kortsteNaam = spelers.get(0);
        for (Speler speler : spelers) {
            if (speler.getNaam().length() < kortsteNaam.getNaam().length()) {
                kortsteNaam = speler;
            } else {
                if (speler.getNaam().length() == kortsteNaam.getNaam().length()) {
                    for (int i = 0; i < speler.getNaam().length(); i++) {
                        char c = speler.getNaam().charAt(i);
                        char d = kortsteNaam.getNaam().charAt(i);
                        if (c > d) {
                            kortsteNaam = speler;
                            break;
                        } else {
                            if (c < d) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        setSpelerAanBeurt(kortsteNaam);

    }

    public boolean isEindeSpelBereikt() {
        return spelers.stream().anyMatch((speler) -> (speler.getNiveau() >= 10));
    }

    public void verminderLevel() {
        spelerAanBeurt.setNiveau((spelerAanBeurt.getNiveau()) - (bovensteKaartSpel.getLevelMin()));

    }

    public int toonVerminderingLeven() {
        return bovensteKaartSpel.getLevelMin();
    }

    public void geefSpelerKaart() {
        spelerAanBeurt.addKaartenInHand(bovensteKaartSpel);
    }

    public void voegCurseAanStapelKerkerKaarten() {
        kerkerKaarten.add((Curse) (bovensteKaartSpel));
    }

    public boolean maxKaarten() {
        return spelerAanBeurt.maxKaarten();
    }

    public Speler getSpelerMaaktKeuze() {
        return spelerMaaktKeuze;
    }

    public void setSpelerMaaktKeuze(Speler spelerMaaktKeuze) {
        this.spelerMaaktKeuze = spelerMaaktKeuze;

    }

    public void volgendeSpelerKeuze() {
        setSpelerMaaktKeuze(spelers.get((geefIndex(spelerMaaktKeuze) < aantal - 1) ? geefIndex(spelerMaaktKeuze) + 1 : 0));
    }

    public Gevecht getGevecht() {
        return gevecht;
    }

    public void setGevecht(Gevecht gevecht) {
        this.gevecht = gevecht;
    }

    public void maakGevechtAan(Gevecht gevecht) {
        setGevecht(gevecht);
        gevecht.addSpelerAanBeurt(spelerAanBeurt);
        gevecht.addMonster((Monster) bovensteKaartSpel);
        gevecht.setSpel(this);
    }

    public void setHelp(int hulpVragen) {
        gevecht.setHelp(hulpVragen);
    }

    public boolean isHelp() {
        return gevecht.isHelp();
    }

    public void addSpelerTegen() {
        gevecht.addSpelerTegen(spelerMaaktKeuze);
    }

    public void addSpelerVoor() {
        gevecht.addSpelerVoor(spelerMaaktKeuze);
    }

    public String watIsBovensteKaart() {
        String ditIsBovensteKaart = "";
        switch (bovensteKaartSpel.getClass().getSimpleName()) {
            case "Monster":
                ditIsBovensteKaart = "monster";
                break;
            case "Curse":
                ditIsBovensteKaart = "curse";
                break;
            case "DConsumable":
            case "Equipment":
            case "Race":
            case "TConsumable":
                ditIsBovensteKaart = "geefInHand";
                break;
        }
        return ditIsBovensteKaart;
    }

    private int geefIndex(Speler speler) {
        int indexSpeler = 0;
        for (Speler s : spelers) {
            if (s.equals(speler)) {
                indexSpeler = spelers.indexOf(s);
            }
        }
        return indexSpeler;
    }

    public int equipmentBonus(Speler speler) {
        return speler.equipmentBonus();
    }

    public int geefSpelerzijdeNiveau() {
        return gevecht.berekenSpelerKant();
    }

    public int geefMonsterZijdeNiveau() {
        return gevecht.berekenMonsterKant();
    }

    public String toonKaartenInHand(int wie) {

        if (wie == 1) {
            List<Kaart> kaartenInHand = spelerAanBeurt.getKaartenInHand();
            String output = "";
            for (Kaart k : kaartenInHand) {
                output += String.format("%s%n", k.toString());
            }
            return output;
        } else {
            List<Kaart> kaartenInHand = spelerMaaktKeuze.getKaartenInHand();
            String output = "";
            for (Kaart k : kaartenInHand) {
                output += String.format("%s%n", k.toString());
            }
            return output;
        }
    }

    public int aantalKaartenInHand(int wie) {
        if (wie == 1) {
            return spelerAanBeurt.getKaartenInHand().size();
        } else {
            return spelerMaaktKeuze.getKaartenInHand().size();
        }
    }

    public void legKaart(int kies, int wie) {
        Kaart kaart;
        if (wie == 1) {
            if (kies <= spelerAanBeurt.getKaartenInHand().size() && kies > 0) {
                switch (spelerAanBeurt.getKaartenInHand().get(kies - 1).getClass().getSimpleName()) {
                    case "TConsumable":
                        kaart = spelerAanBeurt.verwijderKaart(kies);
                        if (kaart.getIsLevelUp() == 1) {
                            spelerAanBeurt.levelUp();
                        } else {
                            gevecht.legTConsumableVoor(kaart);
                        }
                        schatKaarten.add((SchatKaart) (kaart));
                        break;
                    case "DConsumable":
                        kaart = spelerAanBeurt.verwijderKaart(kies);
                        gevecht.legDConsumable(kaart);
                        kerkerKaarten.add((KerkerKaart) (kaart));
                        break;
                    case "Equipment":
                        kaart = spelerAanBeurt.verwijderKaart(kies);
                        spelerAanBeurt.legEquipment(kaart);
                        break;
                    case "Race":
                        kaart = spelerAanBeurt.verwijderKaart(kies);
                        spelerAanBeurt.legRace(kaart);
                        break;
                    case "Monster":
                        kaart = spelerAanBeurt.verwijderKaart(kies);
                        gevecht.addMonster((Monster) kaart);
                        break;
                    default:
                        throw new IllegalArgumentException(labels.getString("Deze_kaart_kun_je_nu_niet_leggen"));

                }
            } else if (kies != 0) {
                throw new IllegalArgumentException(labels.getString("verkeerd_getal"));
            }

        } else {
            if (kies <= spelerMaaktKeuze.getKaartenInHand().size() && kies > 0) {
                switch (spelerMaaktKeuze.getKaartenInHand().get(kies - 1).getClass().getSimpleName()) {
                    case "TConsumable":
                        kaart = spelerMaaktKeuze.verwijderKaart(kies);
                        if (kaart.getIsLevelUp() == 1) {
                            spelerMaaktKeuze.levelUp();
                        } else {

                            if (gevecht.helptInGevecht(spelerMaaktKeuze)) {
                                gevecht.legTConsumableVoor(kaart);
                            } else {
                                gevecht.legTConsumableTegen(kaart);
                            }
                        }
                        schatKaarten.add((SchatKaart) (kaart));
                        break;
                    case "DConsumable":
                        kaart = spelerMaaktKeuze.verwijderKaart(kies);
                        gevecht.legDConsumable(kaart);
                        kerkerKaarten.add((KerkerKaart) (kaart));
                        break;
                    case "Monster":
                        kaart = spelerMaaktKeuze.verwijderKaart(kies);
                        gevecht.addMonster((Monster) kaart);
                        break;
                    default:
                        throw new IllegalArgumentException(labels.getString("Deze_kaart_kun_je_nu_niet_leggen"));
                }
            } else if (kies != 0) {
                throw new IllegalArgumentException(labels.getString("verkeerd_getal"));
            }

        }
    }

    public String geefMonsters() {
        return gevecht.geefMonsters();
    }

    public String geefKaartenOpTafel(int wie) {
        if (wie == 1) {
            return spelerAanBeurt.toStringKaartenOpTafel();
        } else {
            return spelerMaaktKeuze.toStringKaartenOpTafel();
        }
    }

    public String geefDetailsSpelershulp() {
        String output = "";
        int i = 1;
        for (Speler speler : spelers) {
            output += String.format("%n%nSpeler %d:%n%s%nStatus: %s%n", i, speler.toString(), gevecht.wieHelpt(speler));
            i++;
        }
        return output;
    }

    public String uitkomstGevecht() {
        return gevecht.uitkomstGevecht();
    }

    public void afloopGevecht() {
        gevecht.afloopGevecht();
        List<Monster> monster = gevecht.getMonster();
        for (Monster m : monster) {
            kerkerKaarten.add(m);
            
        }
        gevecht.verwijderMonster();

    }

    public void legKaartBeheer(int kies) {
        Kaart kaart;

        if (kies <= spelerAanBeurt.getKaartenInHand().size() && kies > 0) {
            switch (spelerAanBeurt.getKaartenInHand().get(kies - 1).getClass().getSimpleName()) {
                case "TConsumable":
                    kaart = spelerAanBeurt.verwijderKaart(kies);
                    if (kaart.getIsLevelUp() == 1) {
                        spelerAanBeurt.levelUp();
                        schatKaarten.add((SchatKaart) (kaart));
                    } else {
                        spelerAanBeurt.addKaartenInHand(kaart);
                        throw new IllegalArgumentException(labels.getString("Deze_kaart_kun_je_nu_niet_leggen"));
                    }
                    break;
                case "Equipment":
                    kaart = spelerAanBeurt.verwijderKaart(kies);
                    spelerAanBeurt.legEquipment(kaart);
                    break;
                case "Race":
                    kaart = spelerAanBeurt.verwijderKaart(kies);
                    spelerAanBeurt.legRace(kaart);
                    break;
                default:
                    throw new IllegalArgumentException(labels.getString("Deze_kaart_kun_je_nu_niet_leggen"));

            }
        } else if (kies != 0) {
            throw new IllegalArgumentException(labels.getString("verkeerd_getal"));
        }
    }

    public String spelerSituatie() {
        return String.format("%s%n---------------------%n%s%n%s%n%s", labels.getString("spelers_situatie"), spelerAanBeurt.toString(), labels.getString("kaarten_in_hand"), toonKaartenInHand(1));
    }

    public void verkoopHand(int kies) {
        spelerAanBeurt.verkoopHand(kies);
    }

    public int aantalKaartenOpTafel(int wie) {
        if (wie == 1) {
            return spelerAanBeurt.getKaartenOpTafel().size();
        } else {
            return spelerMaaktKeuze.getKaartenOpTafel().size();
        }
    }

    public void verkoopTafel(int kies2) {
        spelerAanBeurt.verkoopTafel(kies2);
    }

    public String overzichtVerkoop() {
        return spelerAanBeurt.overzichtVerkoop();
    }

    public void verkoopKaarten() {
        int totaalVerkoopPrijs = 0;
        for (Kaart kaart : spelerAanBeurt.getTeVerkopenKaarten()) {
            totaalVerkoopPrijs += kaart.getVerkoopPrijs();
            switch (kaart.getClass().getSimpleName()) {
                case "TConsumable":
                case "Equipment":
                    schatKaarten.add((SchatKaart) kaart);
                    break;
                default:
                    kerkerKaarten.add((KerkerKaart) kaart);
                    break;
            }
        }
        spelerAanBeurt.verwijderKaarten();
        if ((spelerAanBeurt.getNiveau() + (totaalVerkoopPrijs / 1000)) < 10) {
            spelerAanBeurt.setNiveau(spelerAanBeurt.getNiveau() + (totaalVerkoopPrijs / 1000));
        } else {
            throw new IllegalArgumentException(labels.getString("verkoop_max_9"));
        }
    }

    public List<Speler> getSpelers() {
        List<Speler> spelerVolgorde = spelers;
        while (true) {
            if (spelerVolgorde.get(0).getNaam().equals(spelerAanBeurt.getNaam())) {
                return spelerVolgorde;
            } else {
                Speler speler = spelerVolgorde.remove(0);
                spelerVolgorde.add(speler);
            }
        }
    }

    public String afrondenSpel() {
        for(Speler speler : spelers){
            if(speler.getNiveau() == 10){
                return String.format("%s %s%n", labels.getString("winnaar"), speler.getNaam());
            }
        }
        return null;
    }
}
