package metier;
public class Produit {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((categ == null) ? 0 : categ.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Produit other = (Produit) obj;
		if (ID != other.ID)
			return false;
		if (categ == null) {
			if (other.categ != null)
				return false;
		} else if (!categ.equals(other.categ))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		if (visual == null) {
			if (other.visual != null)
				return false;
		} else if (!visual.equals(other.visual))
			return false;
		return true;
	}

	private double prix; 
	private int ID;
	private String nom, desc, visual;
	private Categorie categ;

	public Produit() {
		super();
	}

	public Produit(double prix, int iD, String nom, String desc, String visual, Categorie categ) {
		super();
		this.prix = prix;
		ID = iD;
		this.nom = nom;
		this.desc = desc;
		this.visual = visual;
		this.categ = categ;
	}

	@Override
	public String toString() {
		return "ID : " + ID + ", nom : "+nom+", description : "+desc+", tarif : "+prix+", visuel : "+visual+", (categorie : "+categ+" )" ;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVisual() {
		return visual;
	}

	public void setVisual(String visual) {
		this.visual = visual;
	}

	public Categorie getCateg() {
		return categ;
	}

	public void setCateg(Categorie categ) {
		this.categ = categ;
	}
}
