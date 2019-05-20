package gui;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;

public class HoofdPaneel extends BorderPane {

    private final DomeinController controller;
    private final TaalScherm taalScherm;
    private RegistratieScherm regScherm;
    private NaamScherm naamScherm;

    public HoofdPaneel(DomeinController controller) {
        this.controller = controller;
        controller.registreer();
        this.taalScherm = new TaalScherm(controller, this);

        voegComponentenToe();
    }

    private void voegComponentenToe() {
        setCenter(taalScherm);
    }

    public void taalGekozen(DomeinController controller) {
        this.regScherm = new RegistratieScherm(controller, this);
        setCenter(regScherm);
    }

    public void aantalGekozen() {
       this.naamScherm = new NaamScherm(controller, this);
       setCenter(naamScherm);
    }

    public void namenIngegeven() {
        
    }

}
