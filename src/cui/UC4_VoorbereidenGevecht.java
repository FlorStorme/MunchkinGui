package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UC4_VoorbereidenGevecht {

    private final Scanner input = new Scanner(System.in);
    private final DomeinController dc;

    public UC4_VoorbereidenGevecht(DomeinController dc) {
        this.dc = dc;
    }

    public void voorbereidenGevecht() {
        dc.maakGevechtAan();
        dc.setHelp(hulpVragen());
        kaartSpelen(1);
        keuzesTegenspeler();
        UC6_VechtMonster uc6 = new UC6_VechtMonster(dc);
        uc6.vechtMonster();
        
    }

    private int hulpVragen() {
        boolean flag = true;
        int help = 0;
        do {
            try {

                System.out.println(labels.getString("hulp_gewenst"));
                help = input.nextInt();
                if (help == 1 || help == 2) {
                    flag = false;
                } else {
                    throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer"));
                }

            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("verkeerd_getal_beheer"));  
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));      
                input.nextLine();
            }
        } while (flag);
        return help;
    }

    private void kaartSpelen(int wie) {  // als 1 wordt meegegeven dan is het spelerAanBeurt die een kaart wenst te spelen
        int kaartSpelen;                 // bij 2 is het de spelerAanKeuze.
        int teller = 0;
        boolean flag = true;
        do {
            try {

                do {

                    System.out.println(labels.getString("wil_je_een_kaart_spelen"));
                    kaartSpelen = input.nextInt();
                    if (kaartSpelen == 1 || kaartSpelen == 2) {
                        if (kaartSpelen == 1) {
                            UC5_SpeelKaart uc5 = new UC5_SpeelKaart(dc);
                            uc5.speelKaart(wie);
                            teller++;
                        } else {
                            if (teller > 0) {
                                break;
                            }
                            toonBeknopteSpelSituatie();
                        }
                    } else {
                        throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer"));  
                    }

                } while (kaartSpelen == 1);
                flag = false;
            } catch (IllegalArgumentException ie) {
                System.err.println(ie.getMessage());
                input.nextLine();
            } catch (InputMismatchException ie) {
                System.err.println(labels.getString("verkeerd_getal_beheer")); 
                input.nextLine();
            } catch (Exception e) {
                System.err.println(labels.getString("algemene_fout"));     
                input.nextLine();
            }
        } while (flag);

    }

    private void keuzesTegenspeler() {
        boolean flag = true;
        do {
            try {

                do {

                    if (dc.isHelp()) {
                        System.out.printf("%s: %s", dc.geefNaamSpelerMaaktKeuze(), labels.getString("niets_doen_of_tegenwerken_of_helpen"));
                    } else {
                        System.out.printf("%s: %s", dc.geefNaamSpelerMaaktKeuze(), labels.getString("niets_doen_of_tegenwerken"));
                    }
                    int keuzeSpeler = input.nextInt();
                    switch (keuzeSpeler) {
                        case 1:                                    
                            break;                                   
                        case 2:                                      
                            dc.addSpelerTegen();
                            kaartSpelen(2);
                            break;
                        case 3:
                            if(dc.isHelp()){
                            dc.addSpelerVoor();
                            kaartSpelen(2);
                            break;
                            }else{
                                throw new IllegalArgumentException(labels.getString("verkeerd_getal_beheer"));
                            }  
                        default:
                            throw new IllegalArgumentException(dc.isHelp()?labels.getString("verkeerd_getal_actie"):labels.getString("verkeerd_getal_beheer"));
                    }
                    dc.volgendeSpelerKeuze();
                } while ((dc.geefNaamSpelerAanBeurt().equals(dc.geefNaamSpelerMaaktKeuze())) == false);
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

    private void toonBeknopteSpelSituatie() {
        System.out.println(dc.geefMonsters());
        System.out.printf("%s: %d%n%s: %d%n", labels.getString("niveau_spelerzijde"), dc.geefSpelerzijdeNiveau(), labels.getString("niveau_monsterzijde"), dc.geefMonsterZijdeNiveau());
        System.out.printf("%s", dc.geefDetailsSpelers());

    }

}
