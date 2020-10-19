package application.controller;

	import daofactory.DAOFactory;
import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.FlowPane;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.VBox;
	import metier.Categorie;

	public class Controller_ajoutCateg {

	    @FXML
	    private FlowPane fp_main;

	    @FXML
	    private VBox vb_main;

	    @FXML
	    private GridPane gp_saisie;

	    @FXML
	    private Label lbl_name;

	    @FXML
	    private Label lbl_id;

	    @FXML
	    private TextField tf_categname;

	    @FXML
	    private TextField tf_visuel;

	    @FXML
	    private FlowPane fp_create;

	    @FXML
	    private Button btn_create_categ;

	    @FXML
	    private Label lbl_recapcreatcateg;

	    @FXML
	    void creerCateg() {
	    	String visuel=tf_visuel.getText().trim();
	    	String nom=tf_categname.getText().trim();
	    	
	    	if (visuel.isEmpty() || nom.isEmpty()) {
	    		lbl_recapcreatcateg.setText("un ou plusieurs champ(s) vide(s)");
	    	 
	    	}
	    	else {
	    		Categorie categorie = new Categorie();
	    		categorie.setTitre(nom);
	    		categorie.setVisual(visuel);
	    		DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
	    		dao.getCategorieDAO().create(categorie);
	    		lbl_recapcreatcateg.setText("Categorie : " + nom + " (" + visuel + ")");
	    		tf_categname.clear();
	    		tf_visuel.clear();
	    		
	    	}
	    }
	}


