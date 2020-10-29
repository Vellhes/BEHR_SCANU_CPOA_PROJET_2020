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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Categorie;
import metier.Commande;
import metier.Produit;

public class Controller_gestionProduit implements Initializable {

	DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
	
	Produit produit = new Produit();
	
	@FXML
	private GridPane tab_pane;
	
	@FXML
	private GridPane modif_pane;
	
    @FXML
    private TableView<Produit> tab_produit;

    @FXML
    private TableColumn<Produit, Integer> col_id;

    @FXML
    private TableColumn<Produit, String> col_name;

    @FXML
    private TableColumn<Produit, Integer> col_categ;

    @FXML
    private TableColumn<Produit, Double> col_prix;

    @FXML
    private Label lbl_affiche1;

    @FXML
    private Label lbl_affiche2;
    
    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_suppr;

    @FXML
    private Label lbl_erreur1;

    @FXML
    private Label lbl_erreur2;

    @FXML
    private Label lbl_categ_rech;

    @FXML
    private ChoiceBox<Categorie> cb_categ_rech;

    @FXML
    private Label lbl_prix_rech;

    @FXML
    private TextField tf_prix_rech;
    
    @FXML
    private Button btn_rech;
    
    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_desc;

    @FXML
    private Button btn_valider;

    @FXML
    private TextField tf_prix;

    @FXML
    private TextField tf_visual;
    
    @FXML
    private Label lbl_prix;
    
    @FXML
    private Label lbl_visual;
    
    @FXML
    private Label lbl_nom;
    
    @FXML
    private Label lbl_desc;

   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableContent();
		
		btn_modify.setDisable(true);
		btn_suppr.setDisable(true);
		modif_pane.setDisable(true);
		
		cb_categ_rech.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().getAll()));
		
		tab_produit.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					produit = tab_produit.getSelectionModel().getSelectedItem();
					lbl_affiche1.setText(produit.getID()+" : "+produit.getNom()+" ,"+produit.getPrix()+" ("+produit.getVisual()+")");
					lbl_affiche2.setText(produit.getDesc());
					btn_modify.setDisable(false);
					btn_suppr.setDisable(false);
					lbl_erreur1.setText("");
					lbl_erreur2.setText("");
				}
			}
		});
	}
	
	 @FXML
	 boolean suppr_prod() {
	    	List<Commande> liste = new ArrayList<Commande>();
	    	liste = dao.getCommandeDAO().getAll();
	    	for(int i = 0; i<liste.size();i++) {
	    		for(Entry<Produit, Integer> entry : liste.get(i).getProduits().entrySet()) {
	    			if(entry.getKey().getID()==produit.getID()) {
	    				lbl_erreur1.setText("Produit impossible à supprimer");
	    				lbl_erreur2.setText("(Commande existante comprenant ce produit)");
	    				return false;
	    			}
	    		}
	    	}
	    	dao.getProduitDAO().delete(produit);
	    	Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Produit supprimée");
	        alert.setHeaderText(null);
	        alert.setContentText("Produit "+produit.getNom()+" supprimé avec succès");
	        alert.showAndWait();
	    	return true;
	    }
	
	private void setTableContent() {
		tab_produit.getItems().clear();
		col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_categ.setCellValueFactory(new PropertyValueFactory<>("categ"));
		col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		ObservableList<Produit> data = FXCollections.observableArrayList(
                dao.getProduitDAO().getAll()
		);
		tab_produit.setItems(data);
		
	}
	
	@FXML
	private void recherche() {
		tab_produit.getItems().clear();
		col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_categ.setCellValueFactory(new PropertyValueFactory<>("categ"));
		col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		double prix = Double.parseDouble(tf_prix.getText());
		List<Produit> liste = dao.getProduitDAO().getByCateg(cb_categ_rech.getValue().getId());
		ObservableList<Produit> data = FXCollections.observableArrayList();
		for(int i=0; i<liste.size();i++) {
        	if(liste.get(i).getPrix()<=prix) {
        		data.add(liste.get(i));
        	}
        }
		tab_produit.setItems(data);	
	}
	
	@FXML
	void modifProduit() {
		tab_pane.setDisable(true);
		modif_pane.setDisable(false);
		tf_name.setText(produit.getNom());
		tf_desc.setText(produit.getDesc());
		tf_prix.setText(""+produit.getPrix());
		tf_visual.setText(produit.getVisual());
	}
	
	@FXML
	void validModif() {
		String nom = tf_name.getText().trim();
		String desc = tf_desc.getText().trim();
		String visual = tf_visual.getText().trim();
		Double price = Double.parseDouble(tf_prix.getText().trim());
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catégorie modifiée");
        alert.setHeaderText(null);
        if(nom.isEmpty() || desc.isEmpty() || visual.isEmpty() || tf_prix.getText().isEmpty()) {
        	alert.setTitle("Erreur de modification");
        	alert.setContentText("Champ(s) Vide(s)");
        	alert.showAndWait();
        }
        else {
        	if(price<=0) {
        		alert.setTitle("Erreur de modification");
            	alert.setContentText("Le prix ne peut pas être inférieur ou égal à 0");
            	alert.showAndWait();
        	}
        	else {
        		alert.setContentText("Produit changé : "+nom+", "+price+", "+desc+"("+visual+")");
        		produit.setDesc(desc);
        		produit.setNom(nom);
        		produit.setPrix(price);
        		produit.setVisual(visual);
    			alert.showAndWait();
        		dao.getProduitDAO().update(produit);
        		tab_pane.setDisable(false);
        		tf_name.clear();
        		tf_prix.clear();
        		tf_desc.clear();
        		tf_visual.clear();
        		modif_pane.setDisable(true);
        		lbl_affiche1.setText("");
        		lbl_affiche2.setText("");
        		lbl_erreur1.setText("");
        		lbl_erreur2.setText("");
        		btn_suppr.setDisable(true);
        		btn_modify.setDisable(true);
        		setTableContent();
        	}
        }
	}

}