package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author flors
 */
public class UC8_OpslaanSpel {

    private final DomeinController dc;

    public UC8_OpslaanSpel(DomeinController dc) {
        this.dc = dc;
    }

    public void opslaanSpel() {
        String naamSpel = vraagNaam();
        dc.spelOpslaan();
        dc.spelersOpslaan(naamSpel);
        spelOpgeslagen();
        
    }

    private String vraagNaam() {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        String naamSpel = "";
        do{ 
        try {
            System.out.println(labels.getString("naam_spel"));
            naamSpel = input.nextLine();
            dc.setNaam(naamSpel);
            flag = false;
        } catch (IllegalArgumentException ie) {
            System.err.println(ie.getMessage());
            input.nextLine();
        } catch (Exception e) {
            System.err.println(labels.getString("algemene_fout"));
            input.nextLine();
        }
        }while(flag);
        return naamSpel;
    }

    private void spelOpgeslagen() {
        System.out.printf("-------------------------%n%s%n--------------------%n%s%n", labels.getString("spel_opgeslagen"), labels.getString("bedankt"));
        dc.spelAfsluiten();
    }
}
