package domein;

import static domein.DomeinController.labels;

public class SchatKaart extends Kaart {

    private int verkoopPrijs;

    public SchatKaart() {
    }

    @Override
    public int getVerkoopPrijs() {
        return verkoopPrijs;
    }

    public void setVerkoopPrijs(int verkoopPrijs) {
        this.verkoopPrijs = verkoopPrijs;
    }

    @Override
    public String toString() {
        return String.format("%s%s: %d%n", super.toString(), labels.getString("verkoop_prijs"), verkoopPrijs);
    }

}
