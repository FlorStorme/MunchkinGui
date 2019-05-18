package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UC5_SpeelKaart {

    private final Scanner input = new Scanner(System.in);
    private final DomeinController dc;

    public UC5_SpeelKaart(DomeinController dc) {
        this.dc = dc;
    }

    public void speelKaart(int wie) {
        toonKaartenInHand(wie);
        kiesKaart(wie);
        toonSpelSituatie(wie);
    }

    private void toonKaartenInHand(int wie) {
        System.out.printf("%s:%n---------------------------%n%s", labels.getString("dit_zijn_uw_kaarten"), dc.toonKaartenInHand(wie));
    }

    private void kiesKaart(int wie) {
        boolean flag = true;
        do {
            try {
                int kies;
                do {

                    int size = dc.aantalKaartenInHand(wie);
                    if (size == 0) {
                        System.out.println(labels.getString("geen_kaarten_in_hand"));
                    } else {
                        System.out.printf("%s: (%s%s)%n", labels.getString("kies_tussen"), labels.getString("terug_met_nul"), size < 2 ? " 1" : size < 3 ? " 1, 2" : size < 4 ? " 1, 2, 3" : size < 5 ? " 1, 2, 3, 4" : size < 6 ? " 1, 2, 3, 4, 5" : size < 7 ? " 1, 2, 3, 4, 5, 6" : size < 8 ? " 1, 2, 3, 4, 5, 6, 7" : "");
                    }

                    kies = input.nextInt();
                    dc.legKaart(kies, wie);
                    toonKaartenInHand(wie);
                } while (kies != 0);
                flag = false;
            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("geef_getal"));
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                input.nextLine();
            }
        } while (flag);

    }

    private void toonSpelSituatie(int wie) {
        System.out.println(dc.geefMonsters());
        System.out.printf("%s: %d%n%s: %d%n", labels.getString("niveau_spelerzijde"), dc.geefSpelerzijdeNiveau(), labels.getString("niveau_monsterzijde"), dc.geefMonsterZijdeNiveau());
        System.out.printf("%s%n", dc.geefDetailsSpelershulp());
        System.out.printf("%s%n----------------------%n%s%n", labels.getString("dit_zijn_jouw_kaarten_op_tafel"), dc.geefKaartenOpTafel(wie));
    }
}
