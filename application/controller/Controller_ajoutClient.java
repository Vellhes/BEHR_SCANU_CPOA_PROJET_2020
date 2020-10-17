package application.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Controller_ajoutClient {

    @FXML
    private FlowPane fp_main;

    @FXML
    private VBox vb_main;

    @FXML
    private GridPane gp_saisie;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_desc;

    @FXML
    private Label lbl_price;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_mdp;

    @FXML
    private Label lbl_price1;

    @FXML
    private TextField tf_ville;

    @FXML
    private TextField tf_voie;

    @FXML
    private TextField tf_pays;

    @FXML
    private TextField tf_postal;

    @FXML
    private TextField tf_num;

    @FXML
    private FlowPane fp_create;

    @FXML
    private Button btn_create_client;

    @FXML
    private FlowPane fp_display;

    @FXML
    private Label lbl_affiche;

}