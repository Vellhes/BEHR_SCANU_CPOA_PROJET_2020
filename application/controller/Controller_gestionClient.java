package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import daofactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Client;
import metier.Commande;

public class Controller_gestionClient implements Initializable {

	DAOFactory dao = DAOFactory.getDAOFactory(Controller_menu.getChoixPersistance());
	
	Client client = new Client();
	
	@FXML
	private GridPane tab_pane;
	
	@FXML
	private GridPane modif_pane;
	
    @FXML
    private TableView<Client> tab_client;

    @FXML
    private TableColumn<Client, Integer> col_id;

    @FXML
    private TableColumn<Client, String> col_name;

    @FXML
    private TableColumn<Client, String> col_prenom;

    @FXML
    private TableColumn<Client, String> col_identifiant;

    @FXML
    private TableColumn<Client, String> col_num;

    @FXML
    private TableColumn<Client, String> col_voie;

    @FXML
    private TableColumn<Client, String> col_ville;

    @FXML
    private TableColumn<Client, String> col_pays;

    @FXML
    private Label lbl_idnomprenom;

    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_suppr;

    @FXML
    private Label lbl_erreur1;

    @FXML
    private Label lbl_erreur2;

    @FXML
    private Label lbl_mailmdp;

    @FXML
    private Label lbl_adresse;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_prenom;

    @FXML
    private Button btn_valider;

    @FXML
    private TextField tf_ville;

    @FXML
    private TextField tf_num;

    @FXML
    private TextField tf_voie;

    @FXML
    private TextField tf_pays;

    @FXML
    private TextField tf_cp;

    @FXML
    private TextField tf_mdp;

    @FXML
    private TextField tf_identifiant;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTableContent();
		modif_pane.setDisable(true);
		btn_modify.setDisable(true);
		btn_suppr.setDisable(true);
		
		tab_client.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					client = tab_client.getSelectionModel().getSelectedItem();
					lbl_idnomprenom.setText(client.getID() +" : "+client.getNom()+" "+client.getPrenom());
					lbl_mailmdp.setText(client.getIdentifiant()+", "+client.getMdp());
					lbl_adresse.setText(client.getNum()+" "+client.getVoie()+", "+client.getVille()+" ("+client.getPostal()+"), "+client.getPays());
					btn_modify.setDisable(false);
					btn_suppr.setDisable(false);
				}
			}
		});
	}
	
	private void setTableContent() {
		tab_client.getItems().clear();
		col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		col_identifiant.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
		col_num.setCellValueFactory(new PropertyValueFactory<>("num"));
		col_voie.setCellValueFactory(new PropertyValueFactory<>("voie"));
		col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
		col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
		ObservableList<Client> data = FXCollections.observableArrayList(
				dao.getClientDAO().getAll()
		);
		tab_client.setItems(data);
	}

	@FXML
	void modifClient() {
		tab_pane.setDisable(true);
		modif_pane.setDisable(false);
		tf_name.setText(client.getNom());
		tf_prenom.setText(client.getPrenom());
		tf_num.setText(client.getNum());
		tf_voie.setText(client.getVoie());
		tf_pays.setText(client.getPays());
		tf_ville.setText(client.getVille());
		tf_cp.setText(client.getPostal());
		tf_mdp.setText(client.getMdp());
		tf_identifiant.setText(client.getIdentifiant());
	}
	
	@FXML
	void validModif() {
		String nom = tf_name.getText().trim();
		String prenom = tf_prenom.getText().trim();
		String identifiant = tf_identifiant.getText().trim();
		String mdp = tf_mdp.getText().trim();
		String ville = tf_ville.getText().trim();
		String voie = tf_voie.getText().trim();
		String num = tf_num.getText().trim();
		String postal = tf_cp.getText().trim();
		String pays = tf_pays.getText().trim();
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Client modifiée");
        alert.setHeaderText(null);
        if(nom.isEmpty() || prenom.isEmpty() || identifiant.isEmpty() || mdp.isEmpty() || ville.isEmpty() || num.isEmpty() || voie.isEmpty() || postal.isEmpty() || pays.isEmpty()) {
        	alert.setTitle("Erreur de modification");
        	alert.setContentText("Champ(s) Vide(s)");
        	alert.showAndWait();
        }
        else {
        	alert.setContentText("Client changé : "+nom+" "+prenom+", "+num+" "+voie+" "+postal+", "+ville+", "+pays+" ("+identifiant+" : "+mdp);
        	client.setNom(nom);
        	client.setPrenom(prenom);
        	client.setIdentifiant(identifiant);
        	client.setMdp(mdp);
        	client.setVille(ville);
        	client.setVoie(voie);
        	client.setNum(num);
        	client.setPostal(postal);
        	client.setPays(pays);
        	alert.showAndWait();
        	dao.getClientDAO().update(client);
        	tf_name.clear();
            tf_prenom.clear();
            tf_identifiant.clear();
            tf_mdp.clear();
            tf_voie.clear();
            tf_num.clear();
            tf_cp.clear();
            tf_ville.clear();
            tf_pays.clear();
            lbl_idnomprenom.setText("");
            lbl_mailmdp.setText("");
            lbl_adresse.setText("");
            lbl_erreur1.setText("");
            lbl_erreur2.setText("");
            tab_pane.setDisable(false);
            modif_pane.setDisable(true);
            setTableContent();
        }
	}
	
	@FXML
    boolean supprClient() {
		List<Commande> liste = new ArrayList<Commande>();
    	liste = dao.getCommandeDAO().getAll();
    	for(int i = 0; i<liste.size();i++) {
    			if(liste.get(i).getClient()==client) {
    			lbl_erreur1.setText("Client impossible à supprimer");
    			lbl_erreur2.setText("(Commande existante de ce client)");
    			return false;
    		}
    	}
    	dao.getClientDAO().delete(client);
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Produit supprimée");
        alert.setHeaderText(null);
        alert.setContentText("Client "+client.getID()+" : "+client.getNom()+" "+client.getPrenom()+" supprimé avec succès");
        alert.showAndWait();
        setTableContent();
        lbl_idnomprenom.setText("");
        lbl_mailmdp.setText("");
        lbl_adresse.setText("");
        lbl_erreur1.setText("");
        lbl_erreur2.setText("");
    	return true;
    }
}