package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import daofactory.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Controller_menu implements Initializable {

	@FXML
	private MenuItem ajout_categ;

	@FXML
	private MenuItem ajout_prod;

	@FXML
	private MenuItem ajout_client;

	@FXML
	private MenuItem ajout_commande;

	@FXML
	private MenuItem modif_categ;

	@FXML
	private MenuItem modif_prod;

	@FXML
	private MenuItem modif_client;

	@FXML
	private MenuItem modif_commande;

	@FXML
	private MenuItem suppr_categ;

	@FXML
	private MenuItem suppr_prod;

	@FXML
	private MenuItem suppr_client;

	@FXML
	private MenuItem suppr_commande;

	@FXML
	private MenuItem visu_categ;

	@FXML
	private MenuItem visu_prod;

	@FXML
	private MenuItem visu_client;

	@FXML
	private MenuItem visu_commande;

	@FXML
	private Button btn_sql;

	@FXML
	private Button btn_lm;

	@FXML
	private Label lbl_cible;

	private static Persistance persistance = Persistance.MYSQL;

	
	public static Persistance getChoixPersistance() {
		return persistance;
	}

	@FXML
	void ajoutProd(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ajoutProduit.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Ajout Produit");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void ajoutClient(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ajoutClient.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Ajout Client");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}

	@FXML
	void cibleSQL(ActionEvent event) {
		btn_sql.setDisable(true);
		btn_lm.setDisable(false);
		lbl_cible.setText("Votre cible est : SQL");
		persistance = Persistance.MYSQL;

	}

	@FXML
	void cibleLM(ActionEvent event) {
		btn_sql.setDisable(false);
		btn_lm.setDisable(true);
		lbl_cible.setText("Votre cible est : Liste Mémoire");
		persistance = Persistance.ListeMemoire;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_sql.setDisable(true);
	}

}
