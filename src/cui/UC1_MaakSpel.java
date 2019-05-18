package cui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UC1_MaakSpel {

    private final DomeinController dc;
    public static ResourceBundle labels;

    public UC1_MaakSpel(DomeinController dc) {
        this.dc = dc;
    }

    public void start() {
        labels = ResourceBundle.getBundle("ResourceBundle/MyLabels_en_US");

        kiesTaal();
        laden();
        int aantalSpelers = stelAantalSpelersIn();
        voegAlleSpelersToe(aantalSpelers);
        printSpelers();
        UC2_SpeelSpel uc2 = new UC2_SpeelSpel(dc);
        uc2.speelSpel();

    }

    private void kiesTaal() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        do {
            try {
                int lang;

                System.out.println("Choose language([1]English,[2]nederlands or [3]francais): ");

                lang = input.nextInt();
                if (lang < 1 || lang > 3) {
                    throw new IllegalArgumentException("Choose between 1, 2 or 3 please");
                }

                switch (lang) {
                    case 1:
                        Locale.setDefault(new Locale("en", "US"));
                        labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                        break;
                    case 2:
                        Locale.setDefault(new Locale("nl", "BE"));
                        labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                        break;
                    case 3:
                        Locale.setDefault(new Locale("fr", "FR"));
                        labels = ResourceBundle.getBundle("ResourceBundle/MyLabels");
                        break;

                }

                System.out.println(labels.getString("toon_gekozen_taal"));
                flag = false;
            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("verkeerd_getal_actie"));
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                input.nextLine();
            }
        } while (flag);
    }

    private int stelAantalSpelersIn() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        int aantal = 0;
        System.out.println(labels.getString("nieuw_spel"));
        dc.registreer();
        do {
            try {
                System.out.println(labels.getString("invoer_spelers"));
                aantal = input.nextInt();
                dc.aantalInstellen(aantal);
                flag = false;
            } catch (IllegalArgumentException ie) {
                System.err.println(labels.getString(ie.getMessage()));
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("geef_getal"));
                input.nextLine();

            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                input.nextLine();
            }

        } while (flag);
        System.out.printf("%s %d %s %n", labels.getString("toon_aantal_spelers1"), aantal, labels.getString("toon_aantal_spelers2"));
        return aantal;
    }

    private void voegAlleSpelersToe(int aantal) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < aantal; i++) {

            try {

                System.out.printf("%s %d: %s%n", labels.getString("speler"), i + 1, labels.getString("vraag_naam"));
                String naam = input.nextLine();

                System.out.printf("%s %d: %s%n", labels.getString("speler"), i + 1, labels.getString("vraag_geslacht"));
                int geslacht = input.nextInt();
                dc.voegSpelerToe(naam, geslacht);
                input.nextLine();
            } catch (IllegalArgumentException ie) {
                System.err.println(labels.getString(ie.getMessage()));
                i--;
                input.nextLine();
            } catch (InputMismatchException im) {
                System.err.println(labels.getString("geslacht_verkeerd"));
                i--;
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));
                i--;
                input.nextLine();
            }
        }
    }

    private void printSpelers() {
        System.out.printf("%n%s:%n------------------------", labels.getString("overzicht"));
        System.out.println(dc.geefDetailsSpelers());
    }

    private void laden() {
        Scanner input = new Scanner(System.in);
        System.out.println(labels.getString("spel_laden"));
        int laad = input.nextInt();
        if (laad == 1) {
            UC9_LadenSpel uc9 = new UC9_LadenSpel(dc);
            uc9.ladenSpel();
        }
    }

}
