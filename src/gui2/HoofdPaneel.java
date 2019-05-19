package gui2;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;

public class HoofdPaneel extends BorderPane {

    private final DomeinController controller;
    private final TaalScherm taalScherm;
    private final RegistratieScherm regScherm;
    private final NaamScherm naamScherm;

    public HoofdPaneel(DomeinController controller) {
        this.controller = controller;
        this.taalScherm = new TaalScherm(controller, this);
        this.regScherm = new RegistratieScherm(controller, this);
        this.naamScherm = new NaamScherm(controller, this);

        voegComponentenToe();
    }

    private void voegComponentenToe() {
        setCenter(taalScherm);
    }

    public void taalGekozen() {
        setCenter(regScherm);
    }

    void aantalGekozen() {
       setCenter(naamScherm);
    }

}
