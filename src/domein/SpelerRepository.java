package domein;

import persistentie.SpelerMapper;

/**
 *
 * @author flors
 */
public class SpelerRepository {
    private final SpelerMapper mapper;

    public SpelerRepository() {
        mapper = new SpelerMapper();
    }
    
    public void voegToeSpeler(Speler speler, String naam, int teller) {
       mapper.voegToeSpeler(speler, naam, teller);
    }
    
    public void voegToeSpel(){
        
    }
    public boolean bestaatSpel(String naam){
        return mapper.geefSpel(naam)!=null;
    }

    public void spelOpslaan(Spel spel) {
        mapper.spelOpslaan(spel);
    }
   
}
