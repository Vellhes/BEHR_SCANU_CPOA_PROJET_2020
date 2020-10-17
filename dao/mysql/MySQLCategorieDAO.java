package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.CategorieDAO;
import metier.Categorie;

public class MySQLCategorieDAO implements CategorieDAO{

	private static MySQLCategorieDAO instance;
	
	private MySQLCategorieDAO() {}
	
	public static MySQLCategorieDAO getInstance() {
		if(instance==null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}
	
	@Override
	public Categorie getById(int id) {
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM `Categorie` WHERE id_categorie = "+id);
			if(res.next()) {
				Categorie categ = new Categorie();
				categ.setId(res.getInt("id_categorie"));
				categ.setTitre(res.getString("titre"));
				categ.setVisual(res.getString("visuel"));
				return categ;
			} 
			else
				System.out.println("Categorie inexistante");
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
	return null;
	}

	@Override
	public boolean create(Categorie objet) {
		boolean verif = false ;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("INSERT INTO `scanu5u_JAVA`.`Categorie` (`id_categorie`, `titre`, `visuel`) VALUES ("+objet.getId()+", '"+objet.getTitre()+"', '"+objet.getVisual()+"');");
			verif =true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		
		return verif;
	} 

	@Override
	public boolean update(Categorie objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("UPDATE `scanu5u_JAVA`.`Categorie` SET `titre` = '"+objet.getTitre()+"', visuel = '"+objet.getVisual()+"' WHERE `Categorie`.`id_categorie` = "+objet.getId()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}

	@Override
	public boolean delete(Categorie objet) {
		boolean verif = false;
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			stm.executeUpdate("delete from Categorie where id_categorie ="+ objet.getId()+";");
			verif = true;
		}catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return verif;
	}


	@Override
	public List<Categorie> getAll() {
		List<Categorie> liste = new ArrayList<>();
		try {
			Connexion connect = new Connexion();
			Connection cnx = connect.creeConnexion();
			Statement stm = cnx.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM `Categorie`;");
			while(res.next()) {
				Categorie categ = new Categorie();
				categ.setId(res.getInt("id_categorie"));
				categ.setTitre(res.getString("titre"));
				categ.setVisual(res.getString("visuel"));
				liste.add(categ);
			} 
			return liste;
		}
		catch(SQLException sqle) {
			System.out.println("pb select "+sqle.getMessage());
		}
		return null;
	}

}
