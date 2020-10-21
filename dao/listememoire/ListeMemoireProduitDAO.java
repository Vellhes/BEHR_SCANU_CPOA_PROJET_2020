package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import daofactory.DAOFactory;
import daofactory.Persistance;
import metier.Categorie;
import metier.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO {

	 DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	
	private static ListeMemoireProduitDAO instance;
	
	private List<Produit> donnees;
	
	
	private ListeMemoireProduitDAO() {
		this.donnees = new ArrayList<Produit>();
		
		this.donnees.add(new Produit(41.5,1,"Sonic te kiffe","Inspiré par la saga Séga (c'est plus fort que toi !), un pull 100% gamer qui te permettra de faire baver d'envie tes petits camarades de jeu.","pull1.png",new Categorie(1,"Pulls","Pulls.png")));
		this.donnees.add(new Produit(15,2,"La chaleur des rennes","Classique mais efficace, un bonnet dont l'élégance n'est pas à souligner, il vous grattera comme il faut !","bonnet0.png",new Categorie(2,"Bonnets","Bonnets.png")));
		this.donnees.add(new Produit(35,3,"Dall","Joyeux Noël avec nos petits lutins dansants !","bonnet1.png",new Categorie(2,"Bonnets","Bonnets.png")));
	}
	
	public static ListeMemoireProduitDAO getInstance() {
		if(instance==null) {
			instance = new ListeMemoireProduitDAO();
		}
		return instance;
	}
	
	@Override
	public Produit getById(int id) {
		if(this.donnees != null && !this.donnees.isEmpty()) 
			for(Produit produits : this.donnees)
				if(produits.getID()==id)
					return produits;
		return null;
	}

	@Override
	public boolean create(Produit objet) {
		objet.setID(4);
		
		while (this.donnees.contains(objet)) {
			objet.setID(objet.getID()+1);
		}
		objet.setCateg(daos.getCategorieDAO().getById(objet.getCateg().getId()));
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Produit objet) {
		boolean verif = false;
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Produit produits : this.donnees) {
                if (produits.getID() == objet.getID()) {
                	produits.setNom(objet.getNom());
                	produits.setDesc(objet.getDesc());
                	produits.setCateg(daos.getCategorieDAO().getById(objet.getID()));
                	produits.setPrix(objet.getPrix());
                	produits.setVisual(objet.getVisual());
                	verif = true;
                }
            }
        }
        return verif;
	}

	@Override
	public boolean delete(Produit objet) {
		boolean verif = false;
		int id = objet.getID();
		if (this.donnees != null && !this.donnees.isEmpty()) {
			for (Produit produits : this.donnees) {
				if(produits.getID() == id) {
					this.donnees.remove(produits);
					verif = true;
				}
			}
		}
		return verif;
	}

	@Override
	public List<Produit> getAll() {
		List<Produit> liste = new ArrayList<>();
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Produit produits : this.donnees) {
                 liste.add(produits);
            }
            return liste;
        }
        return null;
	}

	@Override
	public List<Produit> getByCateg(int ID) {
		List<Produit> liste = new ArrayList<>();
		if(this.donnees != null && !this.donnees.isEmpty()) {
			for(Produit produits : this.donnees) {
				if(produits.getCateg().getId()==ID) {
					liste.add(produits);
				}
			}
			return liste;
		}
		return null;
	}

}
