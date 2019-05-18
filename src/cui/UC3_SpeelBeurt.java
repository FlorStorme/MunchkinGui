package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UC3_SpeelBeurt {

    private final Scanner input;
    private final DomeinController dc;

    public UC3_SpeelBeurt(DomeinController dc) {
        this.input = new Scanner(System.in);
        this.dc = dc;
    }

    public void speelBeurt() {

        geefSpelOverzicht();
        System.out.println(dc.geefBovensteKerkerKaart());
        doorgaan();
        detecteerKaart();
        beheerKaarten();
        geefSpelOverzicht();

    }

    private void geefSpelOverzicht() {
        System.out.printf("%n-----------------%n%s's %s%n-----------------%n%n%n", dc.geefNaamSpelerAanBeurt(), labels.getString("beurt"));
        System.out.printf("%s:%n-----------------", labels.getString("spel_overzicht"));
        System.out.println(dc.geefDetailsSpelers());
    }

    private void doorgaan() {
        int doorgaan;
        boolean flag = true;
        do {
            try {
                System.out.println(labels.getString("doorgaan"));
                doorgaan = input.nextInt();
                if (doorgaan == 1) {
                    flag = false;
                } else {
                    throw new IllegalArgumentException(labels.getString("verkeerd_getal_doorgaan"));
                }

            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("doorgaan_verkeerd"));
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                input.nextLine();
            }
        } while (flag);

    }

    private void detecteerKaart() {
        switch (dc.watIsBovensteKaart()) {
            case "monster":
                monster();
                break;
            case "curse":
                curse();
                break;
            case "geefInHand":
                geefInhand();
                break;
        }
    }

    private void monster() {
        UC4_VoorbereidenGevecht uc4 = new UC4_VoorbereidenGevecht(dc);
        uc4.voorbereidenGevecht();
    }

    private void curse() {
        dc.verminderLevel();
        toonEffect();
        dc.voegCurseAanStapelKerkerKaarten();
    }

    private void geefInhand() {
        dc.geefSpelerKaart();
    }

    private void toonEffect() {
        System.out.printf("%s %d %s%n", labels.getString("je_verliest_levels1"), dc.toonVerminderingLeven(), labels.getString("je_verliest_levels2"));
    }

    private void beheerKaarten() {
        boolean flag = true;
        do {
            try {
                System.out.printf("%s%n", labels.getString("wens_je_je_kaarten_te_beheren"));
                int beheer = input.nextInt();
                switch (beheer) {
                    case 1:
                        UC7_BeheerKaarten uc7 = new UC7_BeheerKaarten(dc);
                        uc7.beheerKaarten();
                        if (dc.maxKaarten()) {
                            flag = false;
                            break;
                        } else {
                            throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer_max_kaarten"));
                        }

                    case 2:
                        if (dc.maxKaarten()) {
                            flag = false;
                            break;
                        } else {
                            throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer_max_kaarten"));
                        }
                    default:
                        throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer"));
                }
            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException im) {
                System.err.println(labels.getString("geef_getal"));
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                input.nextLine();
            }
        } while (flag);
    }
}
