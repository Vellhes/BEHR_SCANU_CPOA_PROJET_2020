package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import connexion.Connexion;
import dao.CommandeDAO;
import daofactory.DAOFactory;
import daofactory.Persistance;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class MySQLCommandeDAO implements CommandeDAO{

	Map<Produit, Integer> produits = new HashMap<>();
	
	private List<Commande> liste;
	
	private static MySQLCommandeDAO instance;
	
	private MySQLCommandeDAO() {}

	public static MySQLCommandeDAO getInstance() {
		if(instance == null) {
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}
	
	DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	@Override
	public Commande getById(int id) {
		
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Commande where id_commande = "+id);
			if(res.next()) {
				Map<Produit, Integer> produits = new HashMap<>();
				Commande commande = new Commande();
				commande.setId(id);
				commande.setDate(res.getString("date_commande"));
				Client client = daos.getClientDAO().getById(res.getInt("id_client"));
				commande.setClient(client);
				try {
					ResultSet res2 = stm.executeQuery("Select * from Ligne_commande where id_commande = "+id);
					while(res2.next()) {
						Produit prod = new Produit();
						prod.setID(res2.getInt("id_produit"));
						prod.setPrix(res2.getDouble("tarif_unitaire"));
						produits.put(prod, res2.getInt("quantite"));
					}
				}
				catch(SQLException sqle) {
					System.out.println("pb select "+sqle.getMessage());
				}
				commande.setProduits(produits);
				return commande;
			}
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public boolean create(Commande objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Commande` (`id_commande`, `date_commande`, `id_client`) VALUES ("+objet.getId()+",'"+objet.getDate()+"', '"+objet.getClient().getID()+"');");
			for (Entry<Produit, Integer> entry : produits.entrySet()) {
	            System.out.println(entry.getKey());
	            stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Ligne_commande` (`id_commande`, `id_produit`,`quantite`,`tarif_unitaire`) VALUES ("+objet.getId()+","+entry.getKey().getID()+",'"+entry.getValue()+"','"+entry.getKey().getPrix()+"');");
			}
	        verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean update(Commande objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("UPDATE `scanu5u_JAVA`.`Commande` SET `date_commande` = '"+objet.getDate()+"', `id_client` = '"+objet.getClient().getID()+"'");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean delete(Commande objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("delete from Commande where id_commande = " + objet.getId()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}
	
	private Map<Produit, Integer> getLigneCommande(int id){
		try {
			Map<Produit, Integer> produits2 = new HashMap<>();
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res2 = stm.executeQuery("Select * from Ligne_commande where id_commande = "+id);
			while(res2.next()) {
				Produit prod = new Produit();
				prod.setID(res2.getInt("id_produit"));
				prod.setPrix(res2.getDouble("tarif_unitaire"));
				produits2.put(prod, res2.getInt("quantite"));
			}
			return produits2;
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public List<Commande> getAll() {
		
		liste = new ArrayList<>();
		try {
			Map<Produit, Integer> produits = new HashMap<>();
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Commande");
			while(res.next()) {
				Commande commande = new Commande();
				commande.setId(res.getInt("id_commande"));
				commande.setDate(res.getString("date_commande"));
				Client client = daos.getClientDAO().getById(res.getInt("id_client"));
				commande.setClient(client);
				produits = getLigneCommande(res.getInt("id_commande"));
				commande.setProduits(produits);
				liste.add(commande);
				
			}
			return liste;
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public boolean createLC(Commande objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			produits = objet.getProduits();
			for (Entry<Produit, Integer> entry : produits.entrySet()) {
	            stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Ligne_commande` (`id_commande`, `id_produit`,`quantite`,`tarif_unitaire`) VALUES ("+objet.getId()+","+entry.getKey().getID()+",'"+entry.getValue()+"','"+entry.getKey().getPrix()+"');");
			}
	        verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}
}

