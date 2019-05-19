package domein;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DomeinController {

    public final KaartenRepository kaartenRepository;
    public final SpelerRepository spelerRepository;
    public Spel spel;
    public static ResourceBundle labels;

    public DomeinController() {
        labels = ResourceBundle.getBundle("ResourceBundle/MyLabels_en_US");
        kaartenRepository = new KaartenRepository();
        spelerRepository = new SpelerRepository();
    }

    public void registreer() {
        List<KerkerKaart> kerkerKaarten = kaartenRepository.geefAlleKerkerKaarten();
        List<SchatKaart> schatKaarten = kaartenRepository.geefAlleSchatKaarten();
        spel = new Spel(schatKaarten, kerkerKaarten);
    }

    public void aantalInstellen(int aantal) {
        spel.setAantal(aantal);
    }

    public void voegSpelerToe(String naam, int geslacht) throws Exception {
        Speler speler = new Speler(naam, geslacht);
        spel.voegSpelerToe(speler);
    }

    public String geefDetailsSpelers() {
        return spel.geefDetailSpelers();
    }

    public void eersteSpeler() {
        spel.eersteSpeler();
    }

    public boolean isEindeSpelBereikt() {
        return spel.isEindeSpelBereikt();
    }

    public void spelAfsluiten() {
        System.exit(0);
    }

    public String geefNaamSpelerAanBeurt() {
        return spel.getSpelerAanBeurt().getNaam();
    }

    public String geefBovensteKerkerKaart() {
        return String.format("%s:%n-----------------------------------%n%s-------------------%n", labels.getString("getrokken_kaart"), spel.geefBovensteKerkerKaart().toString());
    }

    public String geefBovensteSchatKaart() {
        return spel.geefBovensteSchatKaart().toString();
    }

    public String geefBovensteKaartSpel() {
        return spel.getBovensteKaartSpel().toString();
    }

    public void verminderLevel() {
        spel.verminderLevel();
    }

    public int toonVerminderingLeven() {
        return spel.toonVerminderingLeven();
    }

    public void geefSpelerKaart() {
        spel.geefSpelerKaart();
    }

    public void volgendeSpeler() {
        spel.volgendeSpeler();
    }

    public void voegCurseAanStapelKerkerKaarten() {
        spel.voegCurseAanStapelKerkerKaarten();
    }

    public boolean maxKaarten() {
        return spel.maxKaarten();

    }

    public void volgendeSpelerKeuze() {
        spel.volgendeSpelerKeuze();
    }

    public void maakGevechtAan() {
        Gevecht gevecht = new Gevecht();
        spel.maakGevechtAan(gevecht);
    }

    public void setHelp(int hulpVragen) {
        spel.setHelp(hulpVragen);
    }

    public boolean isHelp() {
        return spel.isHelp();
    }

    public void addSpelerTegen() {
        spel.addSpelerTegen();
    }

    public void addSpelerVoor() {
        spel.addSpelerVoor();
    }

    public String watIsBovensteKaart() {
        return spel.watIsBovensteKaart();
    }

    public String geefNaamSpelerMaaktKeuze() {
        return spel.getSpelerMaaktKeuze().getNaam();
    }

    public int geefSpelerzijdeNiveau() {
        return spel.geefSpelerzijdeNiveau();
    }

    public int geefMonsterZijdeNiveau() {
        return spel.geefMonsterZijdeNiveau();
    }

    public String toonKaartenInHand(int wie) {
        return spel.toonKaartenInHand(wie);
    }

    public int aantalKaartenInHand(int wie) {
        return spel.aantalKaartenInHand(wie);
    }

    public void legKaart(int kies, int wie) {
        spel.legKaart(kies, wie);
    }

    public String geefMonsters() {
        return spel.geefMonsters();
    }

    public String geefKaartenOpTafel(int wie) {
        return spel.geefKaartenOpTafel(wie);
    }

    public String geefDetailsSpelershulp() {
        return spel.geefDetailsSpelershulp();
    }

    public String uitkomsGevecht() {
        return spel.uitkomstGevecht();
    }

    public void afloopGevecht() {
        spel.afloopGevecht();
    }

    public void legKaartBeheer(int kies) {
        spel.legKaartBeheer(kies);
    }

    public String spelerSituatie() {
        return spel.spelerSituatie();
    }

    public void verkoopHand(int kies1) {
        spel.verkoopHand(kies1);
    }

    public int aantalKaartenOpTafel(int wie) {
        return spel.aantalKaartenOpTafel(wie);
    }

    public void verkoopTafel(int kies2) {
        spel.verkoopTafel(kies2);
    }

    public String overzichtVerkoop() {
        return spel.overzichtVerkoop();
    }

    public void verkoopKaarten() {
        spel.verkoopKaarten();
    }

    public void spelersOpslaan(String naam) {
        List<Speler> spelers = spel.getSpelers();
        int teller = 1;
        for (Speler speler : spelers) {
            spelerRepository.voegToeSpeler(speler, naam, teller);
            teller++;
        }
    }

    public boolean bestaatSpel(String naam) {
        return spelerRepository.bestaatSpel(naam);
    }

    public void setNaam(String naamSpel) {
        if (spel.setNaam(naamSpel)) {
            if (spelerRepository.bestaatSpel(naamSpel)) {
                throw new IllegalArgumentException(labels.getString("spel_bestaat_al"));
            }
        }

    }

    public void spelOpslaan() {
        spelerRepository.spelOpslaan(spel);
    }

    public String afrondenSpel() {
        return spel.afrondenSpel();
    }

    public String[] getLanguage() {
        String[] lang = {"English", "Nederlands", "Français"};
        return lang;
    }

    public void kiesTaal(int keuze) {
        switch (keuze) {
            case 0:
                Locale.setDefault(new Locale("en", "US"));
                labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                break;
            case 1:
                Locale.setDefault(new Locale("nl", "BE"));
                labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                break;
            case 2:
                Locale.setDefault(new Locale("fr", "FR"));
                labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                break;

        }
    }

    public void kiesAantal(int keuze) {
        aantalInstellen(keuze + 1);
    }

    public int getAantal() {
        return spel.getAantal();
    }

}
