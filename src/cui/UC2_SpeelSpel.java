package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UC2_SpeelSpel {

    private final DomeinController dc;

    public UC2_SpeelSpel(DomeinController dc) {
        this.dc = dc;
    }

    public void speelSpel() {
        dc.eersteSpeler();
        while (!dc.isEindeSpelBereikt()) {
            kiesActie();
            dc.volgendeSpeler();
        }
        System.out.println(dc.afrondenSpel());
    }

    private void kiesActie() {
        UC3_SpeelBeurt uc3 = new UC3_SpeelBeurt(dc);
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        do {

            try {
                System.out.printf("%s%n-----------------%n%s%n%s%n%s%n%s%n", dc.geefNaamSpelerAanBeurt(), labels.getString("actie1_menu"), labels.getString("actie2_menu"), labels.getString("actie3_menu"), labels.getString("keuze_menu"));
                int keuzeMenu;
                keuzeMenu = input.nextInt();
                if (keuzeMenu < 1 || keuzeMenu > 3) {
                    throw new IllegalArgumentException(labels.getString("verkeerd_getal_actie"));
                }
                switch (keuzeMenu) {
                    case 1:
                        uc3.speelBeurt();
                        break;
                    case 2:
                        UC8_OpslaanSpel uc8 = new UC8_OpslaanSpel(dc);
                        uc8.opslaanSpel();
                        break;
                    case 3:
                        dc.spelAfsluiten();
                        break;
                }
                flag = false;
            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException im) {
                System.err.println(labels.getString("verkeerd_getal_actie"));
                input.nextLine();
            } //catch (Exception e) {
//                System.err.println(labels.getString("algemene_fout"));
//                input.nextLine();
//            }
        } while (flag);
    }
}
