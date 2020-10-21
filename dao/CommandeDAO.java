package dao;

import java.util.List;

import metier.Commande;

public interface CommandeDAO extends DAO<Commande>{
	List<Commande> getAll();
	public abstract boolean createLC(Commande objet);
} 
