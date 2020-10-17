package metier;
import java.util.List;

public class Categorie {
	int id;
	
	private String titre, visual;
	
	private List<Produit> produits;
	

	@Override
	public String toString() {
		return "ID : " + id + ", Titre : " + titre + ", Visuel : " + visual;
	}
 
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		if (titre==null || titre.trim().length()==0)
			throw new IllegalArgumentException("Titre de la categrie Vide");
		this.titre = titre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((visual == null) ? 0 : visual.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (id != other.id)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (visual == null) {
			if (other.visual != null)
				return false;
		} else if (!visual.equals(other.visual))
			return false;
		return true;
	}

	public String getVisual() {
		return visual;
	}

	public void setVisual(String visual) {
		if (visual==null || visual.trim().length()==0)
			throw new IllegalArgumentException("Prenom du client Vide");
		this.visual = visual;
	}

	public Categorie(int id, String titre, String visual) {
		super();
		this.id = id;
		this.titre = titre;
		this.visual = visual;
	}

	public Categorie() {
		super();
	}

	
}
