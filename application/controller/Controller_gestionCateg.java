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
	
	public void setTableContent() {
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("titre"));
		ObservableList<Categorie> data = FXCollections.observableArrayList(
	                dao.getCategorieDAO().getAll()
	    );
		tab_categ.setItems(data);
	}
	
	@FXML
	void supprCateg() {
		List<Produit> produit = dao.getProduitDAO().getAll();
		for(int i = 0 ; i < produit.size() ; i++) {
			if(produit.get(i).getCateg().getId() == categ.getId()) {
				lbl_erreur1.setText("Catégorie impossible à supprimer");
				lbl_erreur2.setText("(produits existants)");
				break;
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
	}
	
	
}
