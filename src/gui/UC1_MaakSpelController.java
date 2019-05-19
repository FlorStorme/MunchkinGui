package gui;


import domein.DomeinController;
import static domein.DomeinController.labels;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flors
 */
public class UC1_MaakSpelController extends Pane {

    @FXML
    private ComboBox<String> cmbLanguage;
    @FXML
    private Label lblChosenLang;
    @FXML
    private Button btnDoorgaan;

    private DomeinController dc;
    

    public UC1_MaakSpelController(DomeinController dc) {
        this.dc = dc;
        
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UC1_MaakSpel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        cmbLanguage.setItems(FXCollections.observableArrayList(dc.getLanguage()));
        
    }

    @FXML
    private void cmbLanguageOnAction(ActionEvent event) {
        int keuze = cmbLanguage.getSelectionModel().getSelectedIndex();
        dc.kiesTaal(keuze);
        lblChosenLang.setText(labels.getString("toon_gekozen_taal"));
        btnDoorgaan.setText(labels.getString("doorgaan"));
    }
    
    @FXML
    private void btnDoorgaanOnAction (ActionEvent event) throws IOException {
        Parent scherm2Parent = FXMLLoader.load((getClass().getResource("Scherm2.fxml")));
        Scene scherm2 = new Scene(scherm2Parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scherm2);
        window.show();
        
    }

}
