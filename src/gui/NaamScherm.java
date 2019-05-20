/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import static domein.DomeinController.labels;
import static java.lang.Integer.parseInt;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author jonad
 */
public class NaamScherm extends Pane {

    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public NaamScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;

        voegComponentenToe();
    }

    private final Label lbl1 = new Label();
    private final Label lbl2 = new Label();
    private final Label lbl3 = new Label();
    private final Label lbl4 = new Label();
    private final Label lbl5 = new Label();
    private final Label lbl6 = new Label();
    private final Label lbl7 = new Label();
    private final Label lbl8 = new Label();
    private final Label lbl9 = new Label();
    private final Label lbl10 = new Label();
    private final Label lbl11 = new Label();
    private final Label lbl12 = new Label();
    private final Label lblSpeler1 = new Label();
    private final Label lblSpeler2 = new Label();
    private final Label lblSpeler3 = new Label();
    private final Label lblSpeler4 = new Label();
    private final Label lblSpeler5 = new Label();
    private final Label lblSpeler6 = new Label();

    private final TextField txtInput1 = new TextField();
    private final TextField txtInput2 = new TextField();
    private final TextField txtInput3 = new TextField();
    private final TextField txtInput4 = new TextField();
    private final TextField txtInput5 = new TextField();
    private final TextField txtInput6 = new TextField();
    private final TextField txtInput7 = new TextField();
    private final TextField txtInput8 = new TextField();
    private final TextField txtInput9 = new TextField();
    private final TextField txtInput10 = new TextField();
    private final TextField txtInput11 = new TextField();
    private final TextField txtInput12 = new TextField();

    private final Button doorgaan = new Button();
    private final Button speler1 = new Button();
    private final Button speler2 = new Button();
    private final Button speler3 = new Button();
    private final Button speler4 = new Button();
    private final Button speler5 = new Button();
    private final Button speler6 = new Button();

    private void voegComponentenToe() {
        int aantal = controller.getAantal();
        for (int i = 0; i < aantal; i++) {
            switch (i) {
                case 0:
                    lblSpeler1.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler1.setLayoutX(50);
                    lblSpeler1.setLayoutY(20);
                    lbl1.setText(labels.getString("vraag_naam"));
                    lbl1.setLayoutX(50);
                    lbl1.setLayoutY(40);
                    lbl7.setText(labels.getString("vraag_geslacht"));
                    lbl7.setLayoutX(50);
                    lbl7.setLayoutY(80);
                    txtInput1.setLayoutX(420);
                    txtInput1.setLayoutY(40);
                    txtInput2.setLayoutX(420);
                    txtInput2.setLayoutY(80);
                    speler1.setText(labels.getString("voeg_speler_toe"));
                    speler1.setLayoutX(600);
                    speler1.setLayoutY(80);
                    this.getChildren().add(speler1);
                    speler1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                String naam = txtInput1.getText();
                                int geslacht = parseInt(txtInput2.getText());
                                controller.voegSpelerToe(naam, geslacht);
                            } catch (IllegalArgumentException ie) {
                                System.err.println(labels.getString(ie.getMessage()));
                            } catch (InputMismatchException im) {
                                System.err.println(labels.getString("geslacht_verkeerd"));
                            } catch (Exception e) {
                                System.err.println(labels.getString("algemene_fout"));
                            }
                        }
                    });
                    this.getChildren().add(txtInput1);
                    this.getChildren().add(txtInput2);
                    break;
                case 1:
                    lblSpeler2.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler2.setLayoutX(50);
                    lblSpeler2.setLayoutY(100);
                    lbl2.setText(labels.getString("vraag_naam"));
                    lbl2.setLayoutX(50);
                    lbl2.setLayoutY(120);
                    lbl8.setText(labels.getString("vraag_geslacht"));
                    lbl8.setLayoutX(50);
                    lbl8.setLayoutY(160);
                    txtInput3.setLayoutX(420);
                    txtInput3.setLayoutY(120);
                    txtInput4.setLayoutX(420);
                    txtInput4.setLayoutY(160);
                    this.getChildren().add(txtInput3);
                    this.getChildren().add(txtInput4);
                    break;
                case 2:
                    lblSpeler3.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler3.setLayoutX(50);
                    lblSpeler3.setLayoutY(180);
                    lbl3.setText(labels.getString("vraag_naam"));
                    lbl3.setLayoutX(50);
                    lbl3.setLayoutY(200);
                    lbl9.setText(labels.getString("vraag_geslacht"));
                    lbl9.setLayoutX(50);
                    lbl9.setLayoutY(240);
                    txtInput5.setLayoutX(420);
                    txtInput5.setLayoutY(200);
                    txtInput6.setLayoutX(420);
                    txtInput6.setLayoutY(240);
                    this.getChildren().add(txtInput5);
                    this.getChildren().add(txtInput6);
                    break;
                case 3:
                    lblSpeler4.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler4.setLayoutX(50);
                    lblSpeler4.setLayoutY(260);
                    lbl4.setText(labels.getString("vraag_naam"));
                    lbl4.setLayoutX(50);
                    lbl4.setLayoutY(280);
                    lbl10.setText(labels.getString("vraag_geslacht"));
                    lbl10.setLayoutX(50);
                    lbl10.setLayoutY(320);
                    txtInput7.setLayoutX(420);
                    txtInput7.setLayoutY(280);
                    txtInput8.setLayoutX(420);
                    txtInput8.setLayoutY(320);
                    this.getChildren().add(txtInput7);
                    this.getChildren().add(txtInput8);
                    break;
                case 4:
                    lblSpeler5.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler5.setLayoutX(50);
                    lblSpeler5.setLayoutY(340);
                    lbl5.setText(labels.getString("vraag_naam"));
                    lbl5.setLayoutX(50);
                    lbl5.setLayoutY(360);
                    lbl11.setText(labels.getString("vraag_geslacht"));
                    lbl11.setLayoutX(50);
                    lbl11.setLayoutY(400);
                    txtInput9.setLayoutX(420);
                    txtInput9.setLayoutY(360);
                    txtInput10.setLayoutX(420);
                    txtInput10.setLayoutY(400);
                    this.getChildren().add(txtInput9);
                    this.getChildren().add(txtInput10);
                    break;
                case 5:
                    lblSpeler6.setText(String.format("%s %d:", labels.getString("speler"), i + 1));
                    lblSpeler6.setLayoutX(50);
                    lblSpeler6.setLayoutY(420);
                    lbl6.setText(labels.getString("vraag_naam"));
                    lbl6.setLayoutX(50);
                    lbl6.setLayoutY(440);
                    lbl12.setText(labels.getString("vraag_geslacht"));
                    lbl12.setLayoutX(50);
                    lbl12.setLayoutY(480);
                    txtInput11.setLayoutX(420);
                    txtInput11.setLayoutY(440);
                    txtInput12.setLayoutX(420);
                    txtInput12.setLayoutY(480);
                    this.getChildren().add(txtInput11);
                    this.getChildren().add(txtInput12);
                    break;
            }
        }
        doorgaan.setText(labels.getString("doorgaan"));
        doorgaan.setLayoutX(100);
        doorgaan.setLayoutY(520);
        doorgaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hoofdPaneel.namenIngegeven();
            }
        });
        this.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, lblSpeler1, lblSpeler2, lblSpeler3, lblSpeler4, lblSpeler5, lblSpeler6, doorgaan);

    }

}
