package application.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import daofactory.Persistance;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import metier.Client;
import metier.Commande;

public class Controller_ajoutCommande implements Initializable{

    @FXML
    private TextField tf_date;

    @FXML
    private ChoiceBox<Client> cb_client;

    @FXML
    private Button btn_create;

    @FXML
    private Label lbl_display;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Persistance persistance = Controller_menu.getChoixPersistance();

		DAOFactory dao = DAOFactory.getDAOFactory(persistance);
		this.cb_client.setItems(FXCollections.observableArrayList(dao.getClientDAO().getAll()));
		Date actuelle = new Date();
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		tf_date.setText(date.format(actuelle)+" 00:00:00");
	}

	@FXML
	void creerCommande() {
		if(cb_client.getValue()==null) {
			lbl_display.setText("Client non sélectionné");
		}
		else {
			Commande commande = new Commande();
			commande.setClient(cb_client.getValue());
			commande.setDate(tf_date.getText());
			Persistance persistance = Controller_menu.getChoixPersistance();
			DAOFactory dao = DAOFactory.getDAOFactory(persistance);
			dao.getCommandeDAO().create(commande);
			lbl_display.setText("Commande : "+tf_date.getText()+", Client : "+cb_client.getValue());
		}
	}
    
}
