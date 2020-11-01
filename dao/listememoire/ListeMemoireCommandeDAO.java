package dao.listememoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.CommandeDAO;
import daofactory.DAOFactory;
import daofactory.Persistance;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

public class ListeMemoireCommandeDAO implements CommandeDAO{

private static ListeMemoireCommandeDAO instance;
	
DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);


	private List<Commande> donnees;
	
	
	private Map<Produit, Integer> produits1, produits2;
	
	private ListeMemoireCommandeDAO() {
		this.produits1 = new HashMap<Produit, Integer>();
		
		this.produits1.put(new Produit(15,2,"La chaleur des rennes","Classique mais efficace, un bonnet dont l'élégance n'est pas à souligner, il vous grattera comme il faut !","bonnet0.png",new Categorie(2,"Bonnets","Bonnets.png")), 2);
		this.produits1.put(new Produit(35,3,"Dall","Joyeux Noël avec nos petits lutins dansants !","bonnet1.png",new Categorie(2,"Bonnets","Bonnets.png")), 1);
		
		this.produits2 = new HashMap<Produit, Integer>();
		
		this.produits2.put(new Produit(41.5,1,"Sonic te kiffe","Inspiré par la saga Séga (c'est plus fort que toi !), un pull 100% gamer qui te permettra de faire baver d'envie tes petits camarades de jeu.","pull1.png",new Categorie(1,"Pulls","Pulls.png")), 4);
		
		this.donnees = new ArrayList<Commande>();
		
		this.donnees.add(new Commande(1,"2020-09-29 18:38:45", new Client(1, "Laroche", "Pierre"), produits1));
		this.donnees.add(new Commande(2,"2002-09-13 00:00:00", new Client(1, "Laroche", "Pierre"), produits2));
	}
	
	public static ListeMemoireCommandeDAO getInstance() {
		if(instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}
		return instance;
	}
	
	@Override
	public Commande getById(int id) {
		if(this.donnees != null && !this.donnees.isEmpty()) 
			for(Commande commandes : this.donnees)
				if(commandes.getId()==id)
					return commandes;
		return null;
	}

	@Override
	public boolean create(Commande objet) {
		objet.setId(3);
		while (this.donnees.contains(objet))
			objet.setId(objet.getId()+1);
		objet.setClient(daos.getClientDAO().getById(objet.getClient().getID()));
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Commande objet) {
		boolean verif = false;
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Commande commandes : this.donnees) {
                if (commandes.getId() == objet.getId()) {
                	commandes.setDate(objet.getDate());
                	commandes.setClient(daos.getClientDAO().getById(objet.getClient().getID()));
                	verif = true;
                }
            }
        }
        return verif;
	}

	@Override
	public boolean delete(Commande objet) {
		boolean verif = false;
		int id = objet.getId();
		if (this.donnees != null && !this.donnees.isEmpty()) {
			for (Commande commandes : this.donnees) {
				if(commandes.getId() == id) {
					this.donnees.remove(commandes);
					verif = true;
				}
			}
		}
		return verif;
	}

	@Override
	public List<Commande> getAll() {
		List<Commande> liste = new ArrayList<>();
		if(this.donnees != null && !this.donnees.isEmpty()) {
			for(Commande commandes : this.donnees) {
					liste.add(commandes);
			}
			return liste;
		}
		return null;
	}

	@Override
	public boolean createLC(Commande objet) {
		if(this.donnees != null && !this.donnees.isEmpty()) {
			for(Commande commandes : this.donnees) {
				if(commandes.getId()==objet.getId()) {
					commandes.setProduits(objet.getProduits());
					return true;
				}
			}
		}
		return false;
	}

}
