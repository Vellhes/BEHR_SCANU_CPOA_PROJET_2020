package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.ClientDAO;
import metier.Client;

public class ListeMemoireClientDAO implements ClientDAO {

	private static ListeMemoireClientDAO instance;
	
	private List<Client> donnees;
	
	private ListeMemoireClientDAO() {
		this.donnees = new ArrayList<Client>();
		
		this.donnees.add(new Client(1,"LAROCHE","Pierre"));
		this.donnees.add(new Client(2,"BEHR","Malo"));
	}
	
	public static ListeMemoireClientDAO getInstance() {
		if(instance==null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}
	
	@Override
	public Client getById(int id) {
		if(this.donnees != null && !this.donnees.isEmpty()) 
			for(Client clients : this.donnees)
				if(clients.getID()==id)
					return clients;
		return null;
	}

	@Override
	public boolean create(Client objet) {
		objet.setID(3);
		while (this.donnees.contains(objet))
			objet.setID(objet.getID()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Client objet) {
		boolean verif = false;
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Client clients : this.donnees) {
                if (clients.getID() == objet.getID()) {
                	clients.setNom(objet.getNom());
                	clients.setPrenom(objet.getPrenom());
                	verif = true;
                }
            }
        }
        return verif;
	}

	@Override
	public boolean delete(Client objet) {
		boolean verif = false;
		int id = objet.getID();
		if (this.donnees != null && !this.donnees.isEmpty()) {
			for (Client clients : this.donnees) {
				if(clients.getID() == id) {
					this.donnees.remove(clients);
					verif = true;
				}
			}
		}
		return verif;
	} 
			

	@Override
	public List<Client> getAll() {
		List<Client> liste = new ArrayList<>();
		if(this.donnees != null && !this.donnees.isEmpty()) {
			for(Client clients : this.donnees) {
				liste.add(clients);
			}
			return liste;
		}
		return null;
	}

}
