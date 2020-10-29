package dao.mysql;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connexion.Connexion;
import dao.ClientDAO;
import metier.Client;


public class MySQLClientDAO implements ClientDAO{

	private Client client;
	private List<Client> liste;
	
	private static MySQLClientDAO instance;
	
	private MySQLClientDAO() {}

	public static MySQLClientDAO getInstance() {
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	@Override
	public boolean create(Client objet) {
		boolean verif = false; 
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Client` (`id_client`, `nom` , `prenom`, `identifiant`, `mot_de_passe`, `adr_numero`, `adr_voie`, `adr_code_postal`, `adr_ville`, `adr_pays`) VALUES ("+objet.getID()+", '"+objet.getNom()+"', '"+objet.getPrenom()+"', '"+objet.getIdentifiant()+"', '"+objet.getMdp()+"', '"+objet.getNum()+"', '"+objet.getVoie()+"', '"+objet.getPostal()+"', '"+objet.getVille()+"', '"+objet.getPays()+"');");
			verif = true;
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean update(Client objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("UPDATE `scanu5u_JAVA`.`Client` SET `nom` = '"+objet.getNom()+"' , `prenom` = '"+objet.getPrenom()+"' WHERE `Client`.`id_client` = "+objet.getID()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean delete(Client objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("delete from Client where id_client = " + objet.getID()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public Client getById(int id) {
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM `Client` WHERE id_client = "+id);
			if(res.next()) {
				client = new Client();
				client.setID(res.getInt("id_client"));
				client.setNom(res.getString("nom"));
				client.setPrenom(res.getString("prenom"));
				client.setIdentifiant(res.getString("identifiant"));
				client.setMdp(res.getString("mot_de_passe"));
				client.setPays(res.getString("adr_pays"));
				client.setPostal(res.getString("adr_code_postal"));
				client.setVille(res.getString("adr_ville"));
				client.setVoie(res.getString("adr_voie"));
				client.setNum(res.getString("adr_numero"));
				return client;
			} 
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public List<Client> getAll() {
		liste = new ArrayList<>();
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Client;");
			while(res.next()) {
				client = new Client();
				client.setID(res.getInt("id_client"));
				client.setNom(res.getString("nom"));
				client.setPrenom(res.getString("prenom"));
				client.setIdentifiant(res.getString("identifiant"));
				client.setMdp(res.getString("mot_de_passe"));
				client.setPays(res.getString("adr_pays"));
				client.setPostal(res.getString("adr_code_postal"));
				client.setVille(res.getString("adr_ville"));
				client.setVoie(res.getString("adr_voie"));
				client.setNum(res.getString("adr_numero"));
				liste.add(client);
			}
			return liste;
		}
		catch(SQLException sqle ) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

}

