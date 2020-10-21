package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.ProduitDAO;
import daofactory.DAOFactory;
import daofactory.Persistance;
import metier.Categorie;
import metier.Produit;

public class MySQLProduitDAO implements ProduitDAO{

	DAOFactory  daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	private List<Produit> liste;
	
	private static MySQLProduitDAO instance;
	
	private MySQLProduitDAO() {}
	
	public static MySQLProduitDAO getInstance() {
		if (instance == null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}
	
	
	@Override
	public Produit getById(int id) {
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Produit where id_produit = "+id+";");
			if(res.next()) {
				Produit produit = new Produit();
				produit = new Produit();
				produit.setID(res.getInt("id_produit"));
				produit.setNom(res.getString("nom"));
				produit.setDesc(res.getString("description"));
				produit.setPrix(res.getDouble("tarif"));
				produit.setVisual(res.getString("visuel"));
				Categorie categ = (daos.getCategorieDAO().getById(res.getInt("id_categorie")));
				produit.setCateg(categ);
				return produit;
			}
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public boolean create(Produit objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Produit` (`id_produit`, `nom`, `description`, `tarif`, `visuel`, `id_categorie`) VALUES ("+objet.getID()+", '"+objet.getNom()+"', '"+objet.getDesc()+"', '"+objet.getPrix()+"', '"+objet.getVisual()+"', '"+objet.getCateg().getId()+"');");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean update(Produit objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("UPDATE `scanu5u_JAVA`.`Produit` SET `nom` = '"+objet.getNom()+"', `description` = '"+objet.getDesc()+"', `tarif` = '"+objet.getPrix()+"', `visuel` = '"+objet.getVisual()+"', `id_categorie` = '"+objet.getCateg().getId()+"' WHERE `Produit`.`id_produit` = "+objet.getID()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean delete(Produit objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("DELETE from Produit where id_produit = "+objet.getID());
			verif=true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+ sqle.getMessage());
		}
		return verif;
	}


	@Override
	public List<Produit> getAll() {
		liste = new ArrayList<>();
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Produit;");
			while(res.next()) {
				Produit produit = new Produit();
				produit = new Produit();
				produit.setID(res.getInt("id_produit"));
				produit.setNom(res.getString("nom"));
				produit.setDesc(res.getString("description"));
				produit.setPrix(res.getDouble("tarif"));
				produit.setVisual(res.getString("visuel"));
				Categorie categ = (daos.getCategorieDAO().getById(res.getInt("id_categorie")));
				produit.setCateg(categ);
				liste.add(produit);
			}
			return liste;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

	@Override
	public List<Produit> getByCateg(int ID) {
		liste = new ArrayList<>();
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("Select * from Produit where id_categorie ="+ID+" ;");
			while(res.next()) {
				Produit produit = new Produit();
				produit = new Produit();
				produit.setID(res.getInt("id_produit"));
				produit.setNom(res.getString("nom"));
				produit.setDesc(res.getString("description"));
				produit.setPrix(res.getDouble("tarif"));
				produit.setVisual(res.getString("visuel"));
				Categorie categ = (daos.getCategorieDAO().getById(res.getInt("id_categorie")));
				produit.setCateg(categ);
				liste.add(produit);
			}
			return liste;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}
	
	
	
}
