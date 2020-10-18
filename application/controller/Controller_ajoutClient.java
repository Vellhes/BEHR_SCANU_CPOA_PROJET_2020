package application.controller;
import daofactory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import metier.Client;

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
    private Label lbl_prenom;

    @FXML
    private Label lbl_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_mdp;

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
    
    @FXML
    void creerClient(ActionEvent event) {
    	String nom = tf_name.getText().trim();
    	String prenom = tf_prenom.getText().trim();
    	String id = tf_id.getText().trim();
    	String mdp = tf_mdp.getText().trim();
    	String ville = tf_ville.getText().trim();
    	String num = tf_num.getText().trim();
    	String voie = tf_voie.getText().trim();
    	String postal = tf_postal.getText().trim();
    	String pays = tf_pays.getText().trim();
    	
    	if(nom.isEmpty() || prenom.isEmpty() || id.isEmpty() || mdp.isEmpty() || ville.isEmpty() || num.isEmpty() || voie.isEmpty() || postal.isEmpty() || pays.isEmpty()) {
    		lbl_affiche.setText("Un ou plusieurs champ(s) vide(s)");
    	}
    	else {
    		Client client = new Client(nom, prenom, ville, num, voie, postal, pays, mdp, id, 0);
    		
    		DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
    		dao.getClientDAO().create(client);
    		
    		lbl_affiche.setText("Client : "+ tf_name.getText() + " "+tf_prenom.getText());
    		
    		tf_name.clear();	tf_prenom.clear();
    		tf_id.clear();	tf_mdp.clear();
    		tf_ville.clear();	tf_num.clear();
    		tf_postal.clear();	tf_voie.clear();
    		tf_pays.clear();
    	}
    }

	
    

}