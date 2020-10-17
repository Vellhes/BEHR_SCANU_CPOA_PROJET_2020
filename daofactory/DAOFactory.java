package daofactory;

import dao.CategorieDAO;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.ProduitDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory(Persistance cible) {
		DAOFactory daoF = null;
		switch (cible) {
		case MYSQL:
		daoF = new MySQLDAOFactory();
		break;
		case ListeMemoire:
		daoF = new ListeMemoireDAOFactory();
		break;
		}
		return daoF;
		}
 
	public abstract ClientDAO getClientDAO();	
	public abstract CommandeDAO getCommandeDAO();
	public abstract CategorieDAO getCategorieDAO();
	public abstract ProduitDAO getProduitDAO();
}
