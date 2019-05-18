package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UC7_BeheerKaarten {

    private final DomeinController dc;
    private final Scanner input = new Scanner(System.in);

    public UC7_BeheerKaarten(DomeinController dc) {
        this.dc = dc;
    }

    public void beheerKaarten() {
        beheerActies();
    }

    private void beheerActies() {

        boolean flag = true;
        do {

            try {

                int actie;

                do {
                    if (dc.aantalKaartenInHand(1) == 0) {
                        System.out.printf("%s%n", labels.getString("kaarten_verkopen"));
                    } else {
                        System.out.printf("%s%n", labels.getString("kaarten_verkopen_of_afleggen"));
                    }
                    actie = input.nextInt();
                    switch (actie) {
                        case 0:
                            break;
                        case 1:
                            verkopen();
                            dc.spelerSituatie();
                            break;
                        case 2:
                            afleggen();
                            dc.spelerSituatie();
                            break;
                        default:
                            throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer"));
                    }
                } while (actie != 0);
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

    private void afleggen() {
        int kies = 0;
        do {
            boolean flag = true;
            do {
                try {
                    System.out.printf("%s:%n---------------------------%n%s", labels.getString("dit_zijn_uw_kaarten"), dc.toonKaartenInHand(1));
                    int size = dc.aantalKaartenInHand(1);
                    if (size == 0) {
                        System.out.println(labels.getString("geen_kaarten_in_hand"));
                    } else {
                        System.out.printf("%s: (%s%s)%n", labels.getString("kies_tussen"), labels.getString("afronden_met_nul"), size < 2 ? " 1" : size < 3 ? " 1, 2" : size < 4 ? " 1, 2, 3" : size < 5 ? " 1, 2, 3, 4" : size < 6 ? " 1, 2, 3, 4, 5" : size < 7 ? " 1, 2, 3, 4, 5, 6" : size < 8 ? " 1, 2, 3, 4, 5, 6, 7" : "");
                    }

                    kies = input.nextInt();
                    dc.legKaartBeheer(kies);
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
        } while (kies != 0);

    }

    private void verkopen() {
        boolean flag = true;
        do {
            try {
                int keuze;
                do {
                    System.out.printf("%s:%n---------------------------%n%s:%n-------------%n%s%n%s:%n--------------%n%s%n", labels.getString("dit_zijn_uw_kaarten"), labels.getString("kaarten_in_hand"), dc.toonKaartenInHand(1), labels.getString("kaarten_op_tafel"), dc.geefKaartenOpTafel(1));
                    System.out.println(labels.getString("kaarten_verkopen_tafel_of_hand"));
                    keuze = input.nextInt();
                    switch (keuze) {
                        case 0:
                            System.out.println(dc.overzichtVerkoop());
                            doorgaan();
                            dc.verkoopKaarten();
                            break;
                        case 1:
                            kiesKaartInHand();
                            break;
                        case 2:
                            kiesKaartOpTafel();
                            break;
                        default:
                    }
                } while (keuze != 0);
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

    private void kiesKaartInHand() {
        int kies1 = 1;
        do {
            System.out.printf("%s:%n---------------------------%n%s", labels.getString("dit_zijn_uw_kaarten"), dc.toonKaartenInHand(1));
            int size = dc.aantalKaartenInHand(1);
            if (size == 0) {
                System.out.println(labels.getString("geen_kaarten_in_hand"));
                kies1 = 0;
            } else {
                System.out.printf("%s: (%s%s)%n", labels.getString("kies_tussen"), labels.getString("afronden_met_nul"), size < 2 ? " 1" : size < 3 ? " 1, 2" : size < 4 ? " 1, 2, 3" : size < 5 ? " 1, 2, 3, 4" : size < 6 ? " 1, 2, 3, 4, 5" : size < 7 ? " 1, 2, 3, 4, 5, 6" : size < 8 ? " 1, 2, 3, 4, 5, 6, 7" : "");
                kies1 = input.nextInt();
                dc.verkoopHand(kies1);
            }
        } while (kies1 != 0);
    }

    private void kiesKaartOpTafel() {
        int kies2 = 1;
        do {
            System.out.printf("%s:%n---------------------------%n%s", labels.getString("dit_zijn_uw_kaarten"), dc.geefKaartenOpTafel(1));
            int size = dc.aantalKaartenOpTafel(1);
            if (size == 0) {
                System.err.println(labels.getString("geen_kaarten_op_tafel"));
                kies2 = 0;
            } else {
                System.out.printf("%s: (%s%s)%n", labels.getString("kies_tussen"), labels.getString("afronden_met_nul"), size < 2 ? " 1" : size < 3 ? " 1, 2" : size < 4 ? " 1, 2, 3" : size < 5 ? " 1, 2, 3, 4" : size < 6 ? " 1, 2, 3, 4, 5" : size < 7 ? " 1, 2, 3, 4, 5, 6" : size < 8 ? " 1, 2, 3, 4, 5, 6, 7" : "");
                kies2 = input.nextInt();
                dc.verkoopTafel(kies2);
            }
        } while (kies2 != 0);
    }

}
