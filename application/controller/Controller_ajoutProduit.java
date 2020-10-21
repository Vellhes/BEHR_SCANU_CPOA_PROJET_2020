package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import daofactory.Persistance;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import metier.Categorie;
import metier.Produit;

public class Controller_ajoutProduit implements Initializable {

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
	private Label lbl_categ;

	@FXML
	private TextField tf_name;

	@FXML
	private TextArea ta_desc;

	@FXML
	private ChoiceBox<Categorie> cb_categ;

	@FXML
	private TextField tf_price;

	@FXML
	private TextField tf_visual;
	
	@FXML
	private Label lbl_euro;

	@FXML
	private FlowPane fp_create;

	@FXML
	private Button btn_create;

	@FXML
	private FlowPane fp_display;

	@FXML
	private Label lbl_affiche;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Persistance persistance = Controller_menu.getChoixPersistance();

		DAOFactory dao = DAOFactory.getDAOFactory(persistance);
		this.cb_categ.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().getAll()));
	}

	@FXML
	void creerProduit() {
		if (tf_name.getText().trim().isEmpty() || ta_desc.getText().trim().isEmpty()
				|| tf_price.getText().trim().isEmpty() || tf_visual.getText().trim().isEmpty()) {
			lbl_affiche.setText("Un ou plusieurs champ(s) vide(s)");
		} else if (cb_categ.getValue() == null) {
			lbl_affiche.setText("Categorie non sélectionnée");
		} else {
			Produit produit = new Produit();
			produit.setCateg(cb_categ.getValue());
			produit.setDesc(ta_desc.getText().trim());
			produit.setNom(tf_name.getText().trim());
			produit.setPrix(Double.parseDouble(tf_price.getText().trim()));
			produit.setVisual(tf_visual.getText().trim());
			DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
			dao.getProduitDAO().create(produit);
			lbl_affiche.setText("Produit : " + tf_name.getText().trim() + " (" + cb_categ.getValue().getTitre() + ") "+ tf_price.getText().trim() + " euros");
			tf_name.clear();
			ta_desc.clear();
			tf_price.clear();
			tf_visual.clear();
		}
	}
}
