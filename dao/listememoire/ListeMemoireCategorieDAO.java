package dao.listememoire;

import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import metier.Categorie;

public class ListeMemoireCategorieDAO implements CategorieDAO{

	private static ListeMemoireCategorieDAO instance;
	
	private List<Categorie> donnees;
	
	private ListeMemoireCategorieDAO() {
		this.donnees = new ArrayList<Categorie>();
		
		this.donnees.add(new Categorie(1,"Pulls", "pulls.png"));
		this.donnees.add(new Categorie(2,"Bonnets", "bonnets.png"));
		this.donnees.add(new Categorie(3,"slip","slip.png"));
		this.donnees.add(new Categorie(4,"slip","slip.png"));
	}
	
	public static ListeMemoireCategorieDAO getInstance() {
		if(instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}
		
		return instance;
	}
	
	@Override
	public Categorie getById(int id) {
		 if (this.donnees != null && !this.donnees.isEmpty()) {
	            for (Categorie categories : this.donnees) {
	                if (categories.getId() == id) {
	                    return categories;
	                }
	            }
	        }
	        return null;
		}

	@Override
	public boolean create(Categorie objet) {
		objet.setId(3);
		
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId()+1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	@Override
	public boolean update(Categorie objet) {
		boolean verif = false;
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Categorie categories : this.donnees) {
                if (categories.getId() == objet.getId()) {
                	categories.setTitre(objet.getTitre());
                	categories.setVisual(objet.getVisual());
                	verif = true;
                }
            }
        }
        return verif;
	}

	@Override
	public boolean delete(Categorie objet) {
		boolean verif = false;
		int id = objet.getId();
		if (this.donnees != null && !this.donnees.isEmpty()) {
			for (Categorie categories : this.donnees) {
				if(categories.getId() == id) {
					this.donnees.remove(categories);
					verif = true;
				}
			}
		}
		return verif;
	}


	@Override
	public List<Categorie> getAll() {
		List<Categorie> liste = new ArrayList<>();
		if (this.donnees != null && !this.donnees.isEmpty()) {
            for (Categorie categories : this.donnees) {
                 liste.add(categories);
            }
            return liste;
        }
        return null;
	}
}

