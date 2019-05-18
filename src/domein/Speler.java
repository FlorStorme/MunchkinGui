package domein;

import static cui.UC1_MaakSpel.labels;
import java.util.ArrayList;
import java.util.List;

public class Speler {

    private String naam;
    private String geslacht;
    private int niveau;
    private String ras;
    private List<Kaart> kaartenInHand;
    private List<Kaart> kaartenOpTafel;
    private List<Kaart> teVerkopenKaarten;

    public Speler() {

    }

    public Speler(String naam, int geslacht) {
        setNaam(naam);
        setGeslacht(geslacht);
        setNiveau(1);
        setRas("human");
        setKaartenOpTafel(new ArrayList<>());
        setTeVerkopenKaarten(new ArrayList<>());
    }

    public String getNaam() {
        return naam;
    }

    public final void setNaam(String naam) {
        String tempNaam = naam.toLowerCase();
        if (naam.length() < 6 || naam.length() > 12) {
            throw new IllegalArgumentException("naam_verkeerd_lengte");
        } else {
            for (int i = 0; i < tempNaam.length(); i++) {
                char c = tempNaam.charAt(i);
                if ((c < 'a' || c > 'z') && c != '-' && c != '_') {
                   throw new IllegalArgumentException("naam_verkeerd_symbool");
                }
            }
            this.naam = naam;
            
        }
    }

    public String getGeslacht() {
        return geslacht;
    }

    public final void setGeslacht(int geslacht) {
        String geslachtS;
        if (geslacht == 1 || geslacht == 2) {
            switch (geslacht) {
                case 1:
                    geslachtS = labels.getString("geslacht_m");
                    this.geslacht = geslachtS;
                    break;
                case 2:
                    geslachtS = labels.getString("geslacht_v");
                    this.geslacht = geslachtS;
                    break;
            }
        } else {
            throw new IllegalArgumentException("geslacht_verkeerd");
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public final void setNiveau(int niveau) {
        // tussen 1 en 10
        if (niveau < 1) {
            this.niveau = 1;

        } else {
            this.niveau = niveau;
        }
    }

    public String getRas() {
        return ras;
    }

    public final void setRas(String ras) {
        this.ras = ras;
    }

    public List<Kaart> getKaartenInHand() {
        return kaartenInHand;
    }

    public void setKaartenInHand(List<Kaart> kaartenInHand) {
        this.kaartenInHand = kaartenInHand;
    }

    public void addKaartenInHand(Kaart kaartNaarHand) {
        this.kaartenInHand.add(kaartNaarHand);
    }

    public void addKaartenOpTafel(Kaart kaartNaarTafel) {
        this.kaartenOpTafel.add(kaartNaarTafel);
    }

    public List<Kaart> getKaartenOpTafel() {
        return kaartenOpTafel;
    }

    public final void setKaartenOpTafel(List<Kaart> kaartenOpTafel) {
        this.kaartenOpTafel = kaartenOpTafel;
    }

    public List<Kaart> getTeVerkopenKaarten() {
        return teVerkopenKaarten;
    }

    public final void setTeVerkopenKaarten(List<Kaart> teVerkopenKaarten) {
        this.teVerkopenKaarten = teVerkopenKaarten;
    }

    public boolean maxKaarten() {
        return kaartenInHand.size() <= 5;
    }

    @Override      // hier moet nog de kaartenOpTafel bij, dit zal moeten gebeuren met een verwijzing naar de toSting van de kaarten
    public String toString() {
        return String.format("%s: %s%n%s: %d%n%s: %s%n%s: %n%s", labels.getString("naam"), naam, labels.getString("niveau"), niveau, labels.getString("geslacht"), geslacht, labels.getString("kaarten"), (kaartenOpTafel == null) ? labels.getString("geen_kaarten_op_tafel") : toStringKaartenOpTafel());

    }

    public int equipmentBonus() {
        int eqBonus = 0;
        if (kaartenOpTafel != null) {
            for (Kaart kaart : kaartenOpTafel) {
                if (kaart.isKaartEquipment(kaart)) {
                    eqBonus += kaart.getStrengthBonus();
                }
            }
        }
        return eqBonus;
    }

    public Kaart verwijderKaart(int kies) {
        return kaartenInHand.remove(kies - 1);
    }

    public void levelUp() {
        setNiveau(niveau + 1);
    }

    public void legEquipment(Kaart kaart) {
        int helm = 0, vest = 0, wapen = 0, schoenen = 0;
        if (kaartenOpTafel != null) {
            for (Kaart k : kaartenOpTafel) {
                if (k.isKaartEquipment(k)) {
                    switch (k.getEquipSlot()) {
                        case "headgear":
                            helm++;
                            break;
                        case "armor":
                            vest++;
                            break;
                        case "footgear":
                            schoenen++;
                            break;
                        case "weapon":
                            wapen++;
                            break;
                        default:
                            throw new IllegalArgumentException("lol");
                    }
                }
            }
            switch (kaart.getEquipSlot()) {
                case "headgear":
                    if (helm == 0) {
                        addKaartenOpTafel(kaart);
                    } else {
                        addKaartenInHand(kaart);
                        throw new IllegalArgumentException(labels.getString("al_headgear"));
                    }
                    break;
                case "armor":
                    if (vest == 0) {
                        addKaartenOpTafel(kaart);
                    } else {
                        addKaartenInHand(kaart);
                        throw new IllegalArgumentException(labels.getString("al_armor"));
                    }
                    break;
                case "footgear":
                    if (schoenen == 0) {
                        addKaartenOpTafel(kaart);
                    } else {
                        addKaartenInHand(kaart);
                        throw new IllegalArgumentException(labels.getString("al_footgear"));
                    }
                    break;
                case "weapon":
                    if (wapen == 0 || wapen == 1) {
                        addKaartenOpTafel(kaart);
                    } else {
                        addKaartenInHand(kaart);
                        throw new IllegalArgumentException(labels.getString("al_weapon"));
                    }
                    break;
                default:
                    addKaartenInHand(kaart);
                    throw new IllegalArgumentException("lol");
            }
        } else {
            addKaartenOpTafel(kaart);
        }

    }

    public void legRace(Kaart kaart) {
        boolean race = false;
        for (Kaart k : kaartenOpTafel) {
            if (k.isKaartRace(k)) {
                race = true;
            }
        }
        if (!race) {
            addKaartenOpTafel(kaart);
        } else {
            addKaartenInHand(kaart);
            throw new IllegalArgumentException(labels.getString("Deze_kaart_kun_je_nu_niet_leggen"));
        }

    }

    public String toStringKaartenOpTafel() {
        String output = "";
        for (Kaart k : kaartenOpTafel) {
            output += String.format("%s%n", k.toString());
        }
        return output;
    }

    public void loseLevels(int badStuff) {
        setNiveau(niveau - badStuff);
    }

    public void verkoopHand(int kies) {
        teVerkopenKaarten.add(kaartenInHand.remove(kies - 1));
    }

    public void verkoopTafel(int kies2) {
        teVerkopenKaarten.add(kaartenOpTafel.remove(kies2 - 1));
    }

    public String overzichtVerkoop() {
        int totaalVerkoopPrijs = 0;
        for (Kaart kaart : teVerkopenKaarten) {
            totaalVerkoopPrijs += kaart.getVerkoopPrijs();
        }
        return String.format("%s: %d%n%s: %d", "Totale verkoopsprijs", totaalVerkoopPrijs, "aantal levels dat je krijgt", totaalVerkoopPrijs / 1000);
    }

    public void verwijderKaarten() {
        teVerkopenKaarten.clear();
    }

   
}
