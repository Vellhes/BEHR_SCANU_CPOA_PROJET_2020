package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import daofactory.Persistance;
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
	private MenuItem ajout_lignecommande;
	
	@FXML
	private MenuItem gestion_categ;

	@FXML
	private MenuItem gestion_prod;

	@FXML
	private MenuItem gestion_client;

	@FXML
	private MenuItem gestion_commande;

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
	void ajoutCateg() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ajoutCateg.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Ajout Catégorie");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void ajoutProd() {
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
	void ajoutClient() {
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
	void ajoutCommande() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ajoutCommande.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Ajout Commande");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void ajoutLigneCommande() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ajoutLigneCommande.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Ajout Ligne Commande");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}

	@FXML
	void gestionCateg() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/gestionCateg.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Gestion Catégories");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void gestionProduit() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/gestionProduit.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Gestion Produits");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void gestionClient() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/gestionClient.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Gestion Clients");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception e) {
			System.out.println("Can't load new window " + e);
		}
	}
	
	@FXML
	void cibleSQL() {
		btn_sql.setDisable(true);
		btn_lm.setDisable(false);
		lbl_cible.setText("Votre cible est : SQL");
		persistance = Persistance.MYSQL;

	}

	@FXML
	void cibleLM() {
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
