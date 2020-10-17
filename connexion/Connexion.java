package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public Connection creeConnexion() {
		String url = 
				"jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/scanu5u_JAVA";
		url += "?serverTimezone=Europe/Paris";
		String login = "scanu5u_appli";
		String pwd = "malo"; Connection maConnexion = null;
		try { 
			maConnexion = DriverManager.getConnection(url, login, pwd);
			} catch (SQLException sqle) {
				System.out.println("Erreur connexion" + sqle.getMessage());
				}
		return maConnexion;
		}
	}
