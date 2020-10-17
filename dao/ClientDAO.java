package dao;
import java.util.List;

import metier.Client;

public interface ClientDAO extends DAO<Client>{
	List<Client> getAll();
}
