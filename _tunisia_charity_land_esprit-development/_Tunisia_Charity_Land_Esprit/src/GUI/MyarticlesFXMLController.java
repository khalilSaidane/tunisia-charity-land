/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Raed
 */
public class MyarticlesFXMLController implements Initializable {

    @FXML
    private TableView<?> tableviewart;
    @FXML
    private TableColumn<?, ?> cid;
    @FXML
    private TableColumn<?, ?> cauthorid;
    @FXML
    private TableColumn<?, ?> cdate;
    @FXML
    private TableColumn<?, ?> ctitle;
    @FXML
    private TableColumn<?, ?> ccontent;
    @FXML
    private TableColumn<?, ?> cimage;
    @FXML
    private TableColumn<?, ?> clikes;
    @FXML
    private Button supprimerart;
    @FXML
    private TextField textsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
