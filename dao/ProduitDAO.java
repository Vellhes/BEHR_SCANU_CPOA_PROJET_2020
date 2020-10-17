package dao;

import java.util.List;

import metier.Produit;

public interface ProduitDAO extends DAO<Produit>{
	List<Produit> getAll();
}
