package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import daofactory.Persistance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import metier.Commande;
import metier.Produit;
import metier.Categorie;

public class Controller_ajoutLigneCommande implements Initializable{

    @FXML
    private FlowPane fp_main;

    @FXML
    private VBox vb_main;

    @FXML
    private GridPane gp_saisie;

    @FXML
    private TextField tf_qte;

    @FXML
    private ChoiceBox<Commande> cb_commande;

    @FXML
    private TextField tf_date;

    @FXML
    private ChoiceBox<Categorie> cb_categ;

    @FXML
    private ChoiceBox<Produit> cb_prod;

    @FXML
    private FlowPane fp_create;

    @FXML
    private Button btn_create_commande;

    @FXML
    private FlowPane fp_display;

    @FXML
    private Label lbl_recapcreate;

    @FXML
    void creerLigneCommande() {
    	String qteS = tf_qte.getText().trim();
    	if(cb_commande.getValue() == null || cb_prod.getValue()==null) {
    		lbl_recapcreate.setText("Champ(s) vide(s)");
    	}
    	else if(qteS == null) {
    		lbl_recapcreate.setText("Quantité nulle");
    	}
    	else {
    		int qte = Integer.parseInt(qteS);
    		Commande commande = cb_commande.getValue();
    		Produit produit = cb_prod.getValue();
    		Map<Produit, Integer> produits = new HashMap<>();
    		produits.put(produit, qte);
    		commande.setProduits(produits);
    		Persistance persistance = Controller_menu.getChoixPersistance();
    		DAOFactory dao = DAOFactory.getDAOFactory(persistance);
    		boolean verif = dao.getCommandeDAO().createLC(commande);
    		if(verif==true) {
    			tf_qte.clear();
    			lbl_recapcreate.setText("Produit : "+produit+" pour la commande "+commande.getId());
    		}
    	}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Persistance persistance = Controller_menu.getChoixPersistance();
		
		DAOFactory dao = DAOFactory.getDAOFactory(persistance);
		this.cb_commande.setItems(FXCollections.observableArrayList(dao.getCommandeDAO().getAll()));
		this.cb_categ.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().getAll()));
		cb_prod.setDisable(true);
		tf_date.setDisable(true);
		cb_categ.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		        Categorie categ = cb_categ.getItems().get((Integer) number2);
		        cb_prod.setItems(FXCollections.observableArrayList(dao.getProduitDAO().getByCateg(categ.getId())));
		        cb_prod.setDisable(false);
		      }
		});
		cb_commande.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
		        Commande commande = cb_commande.getItems().get((Integer) number2);
		        String date = commande.getDate();
		        tf_date.setText(date);
		        /*Date actuelle = new Date();
				DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = (date.format(actuelle)+" 00:00:00")*/;

		      }
		});
	}

}