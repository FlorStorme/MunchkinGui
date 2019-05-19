/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import domein.DomeinController;
import javafx.scene.layout.Pane;

/**
 *
 * @author jonad
 */
public class NaamScherm extends Pane {

    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    NaamScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        voegComponentenToe();
    }

    private void voegComponentenToe() {
     int aantal= controller.getAantal();
     for(int i=0; i<aantal;i++ ){
         
     }
    }

}
