package domein;

import java.util.List;
import persistentie.KaartenMapper;

/**
 *
 * @author flors
 */
public class KaartenRepository {
    private final KaartenMapper mapper;

    public KaartenRepository() {
        mapper = new KaartenMapper();
    }
    
    public List<KerkerKaart> geefAlleKerkerKaarten(){
        List<KerkerKaart> kerkerKaarten = mapper.geefAlleKerkerKaarten();
        return kerkerKaarten;
    }
    
    public List<SchatKaart> geefAlleSchatKaarten(){
        List<SchatKaart> schatKaarten = mapper.geefAlleSchatKaarten();
        return schatKaarten;
    }
}
