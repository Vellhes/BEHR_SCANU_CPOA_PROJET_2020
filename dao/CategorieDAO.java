package dao;

import java.util.List;

import metier.Categorie;

public interface CategorieDAO extends DAO<Categorie>{
	List<Categorie> getAll();
}
