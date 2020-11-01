package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class Controller_gestionCommande implements Initializable {

	DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
	
    Commande commande = new Commande();
    
    Produit produit = new Produit();
	
    @FXML
    private GridPane tab_pane;

    @FXML
    private TableView<Commande> tab_commande;

    @FXML
    private TableColumn<Commande, Integer> col_id;

    @FXML
    private TableColumn<Commande, String> col_date;

    @FXML
    private TableColumn<Commande, Integer> col_client;

    @FXML
    private Label lbl_affiche1;

    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_suppr;

    @FXML
    private Label lbl_erreur;

    @FXML
    private Label lbl_affiche2;

    @FXML
    private Label lbl_produit_rech;

    @FXML
    private ChoiceBox<Produit> cb_produit_rech;

    @FXML
    private Button btn_rech;

    @FXML
    private Label lbl_client_rech;

    @FXML
    private ChoiceBox<Client> cb_client_rech;

    @FXML
    private TableView<Produit> tab_produit;

    @FXML
    private TableColumn<Produit, Integer> col_idproduit;

    @FXML
    private TableColumn<Produit, String> col_produit;

    @FXML
    private TableColumn<Produit, Double> col_prix;

    @FXML
    private GridPane modif_pane;

    @FXML
    private Button btn_valider;

    @FXML
    private Label lbl_visual;

    @FXML
    private ChoiceBox<Client> cb_client;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableCommandeContent();
		modif_pane.setDisable(true);
		btn_suppr.setDisable(true);
		btn_modify.setDisable(true);
		tab_commande.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					commande = tab_commande.getSelectionModel().getSelectedItem();
					setTableProduitContent(commande);
					lbl_affiche2.setText("");
					lbl_affiche1.setText("Montant total : "+commande.getMontantTotal()+"€");
					btn_suppr.setDisable(false);
					btn_modify.setDisable(false);
				}
			}
		});
		
		tab_produit.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					produit = tab_produit.getSelectionModel().getSelectedItem();
					for(Entry<Produit, Integer> entry : commande.getProduits().entrySet()) {
		    			if(entry.getKey()==produit) {
		    				lbl_affiche2.setText("Quantité : "+entry.getValue());
		    			}
		    		}
				}
			}
		});
		
		cb_client.setItems(FXCollections.observableArrayList(dao.getClientDAO().getAll()));
		cb_client_rech.setItems(FXCollections.observableArrayList(dao.getClientDAO().getAll()));
		//cb_produit_rech.setItems(FXCollections.observableArrayList(dao.getProduitDAO().getAll()));
		/*Impossible de laisser la ligne ci-dessus sinon la denêtre ne se lance pas.
		 Le problème vient du remplissage de la CoiceBox et non de la méthode getProduitDAO().getAll()  
		 */
	}
    
    private void setTableCommandeContent() {
    	tab_commande.getItems().clear();
    	tab_produit.getItems().clear();
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	col_client.setCellValueFactory(new PropertyValueFactory<>("client"));
    	ObservableList<Commande> data = FXCollections.observableArrayList(
    			dao.getCommandeDAO().getAll()
    	);
    	tab_commande.setItems(data);
    }
    
    private void setTableProduitContent(Commande comm) {
    	tab_produit.getItems().clear();
    	col_idproduit.setCellValueFactory(new PropertyValueFactory<>("ID"));
    	col_produit.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	List<Produit> liste = new ArrayList<Produit>();
    	for(Entry<Produit, Integer> entry : comm.getProduits().entrySet()) {
    		liste.add(entry.getKey());
    	}
    	ObservableList<Produit> data = FXCollections.observableArrayList(
    			liste
    	);
    	tab_produit.setItems(data);
    }
    
    @FXML
    void modifCommande() {
    	modif_pane.setDisable(false);
    	tab_pane.setDisable(true);
    }

    @FXML
    void validModif() {
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(cb_client.getValue()==null) {
            alert.setTitle("Erreur");
            alert.setContentText("Aucun client sélectionné");
            alert.showAndWait();
    	}
    	else {
    		alert.setTitle("Commande modifiée");
            alert.setContentText("Client modfifié pour la commande "+commande.getId()+" : "+cb_client.getValue());
            alert.showAndWait();
            commande.setClient(cb_client.getValue());
            dao.getCommandeDAO().update(commande);
            modif_pane.setDisable(true);
        	tab_pane.setDisable(false);
        	setTableCommandeContent();
        	btn_suppr.setDisable(false);
			btn_modify.setDisable(false);
    	}
    }
    
    @FXML
    void recherche() {
    	Client clientrech = cb_client_rech.getValue();
    	Produit prodrech = cb_produit_rech.getValue();
    	if(clientrech==null || prodrech==null) {
    		lbl_erreur.setText("Veuillez choisir un client et un produit");
    	}
    	else {
    		List<Commande> listeALL = dao.getCommandeDAO().getAll();
    		List<Commande> listeTRI = new ArrayList<>();
    		for(int i=0; i < listeALL.size() ; i++) {
    			if(listeALL.get(i).getClient()==clientrech) {
    				for(Entry<Produit, Integer> entry : listeALL.get(i).getProduits().entrySet()) {
    					if(entry.getKey()==prodrech) {
    						listeTRI.add(listeALL.get(i));
    					}
    				}
    			}
    		}
    		ObservableList<Commande> data = FXCollections.observableArrayList(
        			listeTRI
        	);
    		tab_commande.getItems().clear();
    		tab_produit.getItems().clear();
    		tab_commande.setItems(data);
    	}
    }

    @FXML
    void supprCommande() {
    	dao.getCommandeDAO().delete(commande);
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Commande supprimée");
        alert.setHeaderText(null);
        alert.setContentText("Commande "+commande.getId()+" supprimée avec succès");
        alert.showAndWait();
        setTableCommandeContent();
    }

}