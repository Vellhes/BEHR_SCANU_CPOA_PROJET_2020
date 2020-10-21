package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import metier.Categorie;
import metier.Produit;

public class Controller_gestionCateg implements Initializable  {

    @FXML
    private TableView<Categorie> tab_categ;

    DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
    
    Categorie categ = new Categorie();
    
    @FXML
    private TableColumn<Categorie, Integer> col_id;

    @FXML
    private TableColumn<Categorie, String> col_name;

    @FXML
    private Label lbl_affiche;

    @FXML
    private Label lbl_erreur1;
    
    @FXML
    private Label lbl_erreur2;
    
    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_suppr;

    @FXML
    private Label lbl_name;
    
    @FXML
    private Label lbl_visual;
    
    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_visual;

    @FXML
    private Button btn_valider;

    @FXML
    private Label lbl_modif;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableContent();
		
		btn_modify.setDisable(true);
		btn_suppr.setDisable(true);
		
		lbl_name.setDisable(true);
		lbl_visual.setDisable(true);
		tf_name.setDisable(true);
		tf_visual.setDisable(true);
		btn_valider.setDisable(true);
		
		tab_categ.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					categ = tab_categ.getSelectionModel().getSelectedItem();
					lbl_affiche.setText(categ.getId() +" : "+categ.getTitre()+" ("+categ.getVisual()+")");
					btn_modify.setDisable(false);
					btn_suppr.setDisable(false);
				}
			}
		});
		
	}
	
	private void setTableContent() {
		tab_categ.getItems().clear();
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("titre"));
		ObservableList<Categorie> data = FXCollections.observableArrayList(
	                dao.getCategorieDAO().getAll()
	    );
		tab_categ.setItems(data);
	}
	
	@FXML
	boolean supprCateg() {
		List<Produit> produit = dao.getProduitDAO().getAll();
		for(int i = 0 ; i < produit.size() ; i++) {
			if(produit.get(i).getCateg().getId() == categ.getId()) {
				lbl_erreur1.setText("Catégorie impossible à supprimer");
				lbl_erreur2.setText("(produits existants)");
				return false;
			}
		}
		dao.getCategorieDAO().delete(categ);
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catégorie supprimée");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie "+categ.getTitre()+" supprimée avec succès");
        alert.showAndWait();
        setTableContent();
		btn_modify.setDisable(true);
		btn_suppr.setDisable(true);
		lbl_affiche.setText("");
		lbl_erreur1.setText("");
		lbl_erreur2.setText("");
		return true;
	}
	
	@FXML
	void modifCateg() {
		tab_categ.setDisable(true);
		btn_suppr.setDisable(true);
		btn_modify.setDisable(true);
		lbl_name.setDisable(false);
		lbl_visual.setDisable(false);
		tf_name.setDisable(false);
		tf_visual.setDisable(false);
		btn_valider.setDisable(false);
	}
	
	@FXML
	void validModif() {
		String title = tf_name.getText().trim();
		String visual = tf_visual.getText().trim();
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catégorie modifiée");
        alert.setHeaderText(null);
		if(title.isEmpty() || visual.isEmpty()) {
			lbl_modif.setText("Champ(s) vide(s)");
		}
		else {
			alert.setContentText("Catégorie "+categ.getId()+" changée : "+categ.getTitre()+" "+categ.getVisual()+
					" -> "+title+" "+visual);
			categ.setTitre(title);
			categ.setVisual(visual);
			dao.getCategorieDAO().update(categ);
			alert.showAndWait();
			tab_categ.setDisable(false);
			btn_suppr.setDisable(false);
			btn_modify.setDisable(false);
			lbl_name.setDisable(true);
			lbl_visual.setDisable(true);
			tf_name.setDisable(true);
			tf_visual.setDisable(true);
			btn_valider.setDisable(true);
			lbl_modif.setText("");
		}
		setTableContent();
	}
}
