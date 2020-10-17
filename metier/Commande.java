package metier;
import java.util.Map;

public class Commande {

	private int id;
	
	public Commande(int id, String date, Client client, Map<Produit, Integer> produits) {
		super();
		this.id = id;
		this.date = date;
		this.client = client;
		this.produits = produits;
	}

	private String date;
	
	private Client client;
	
	private Map<Produit, Integer> produits;
	
	public Commande() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((produits == null) ? 0 : produits.hashCode());
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
		Commande other = (Commande) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (produits == null) {
			if (other.produits != null)
				return false;
		} else if (!produits.equals(other.produits))
			return false;
		return true;
	}

	public Double getMontantTotal() {
		Double resultat = 0d;
		for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
			Produit produit = entry.getKey();
			Integer quantite = entry.getValue();
			resultat += (produit.getPrix() * quantite);
		}
		return resultat;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Map<Produit, Integer> getProduits() {
		return produits;
	}

	public void setProduits(Map<Produit, Integer> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", client=" + client + ", montant total = " + getMontantTotal() + "]";
	}
	
}

