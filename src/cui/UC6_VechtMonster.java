
package cui;

import static cui.UC1_MaakSpel.labels;
import domein.DomeinController;


public class UC6_VechtMonster {
    private final DomeinController dc;

    public UC6_VechtMonster(DomeinController dc) {
        this.dc = dc;
    }

    public void vechtMonster() {
        uitkomstGevecht();
        dc.afloopGevecht();
        speloverzicht();
    }

    private void uitkomstGevecht() {
         System.out.printf("%s: %d%n%s: %d%n%n", labels.getString("niveau_spelerzijde"), dc.geefSpelerzijdeNiveau(), labels.getString("niveau_monsterzijde"), dc.geefMonsterZijdeNiveau());
         System.out.println(dc.uitkomsGevecht());
    }

    private void speloverzicht() {
          System.out.printf("%n-----------------%n%s's %s%n-----------------%n%n%n", dc.geefNaamSpelerAanBeurt(), labels.getString("beurt"));
          System.out.println(dc.geefDetailsSpelers());
    }

    
    
}
