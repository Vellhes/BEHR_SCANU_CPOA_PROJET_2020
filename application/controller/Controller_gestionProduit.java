package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Categorie;
import metier.Produit;

public class Controller_gestionProduit implements Initializable {

	DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
	
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
    private Button btn_modify;

    @FXML
    private Button btn_suppr;

    @FXML
    private Label lbl_erreur1;

    @FXML
    private Label lbl_erreur2;

    @FXML
    private Label lbl_affiche2;

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
    private ChoiceBox<?> cb_categ;

    @FXML
    void supr_prod() {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableContent();
		
		btn_modify.setDisable(true);
		btn_suppr.setDisable(true);
		
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

}