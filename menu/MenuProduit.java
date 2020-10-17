package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Categorie;
import metier.Produit;

public class MenuProduit {
	  @SuppressWarnings("resource")
	public static void Produit(DAOFactory daos, int action){
	    boolean verif;
	    Scanner scI = new Scanner(System.in);
		Scanner scS = new Scanner(System.in);
		Scanner scD = new Scanner(System.in);
	
	//Ajout
	if(action==1) {
		System.out.println("Saisissez le nom puis la description, le tarif, le visuel du produit et enfin l'ID de sa categorie");
		String nom = scS.nextLine();
		String desc = scS.nextLine();
		double tarif = scD.nextDouble();
		String visual = scS.nextLine();
		int categorie = scI.nextInt();
		Categorie categ = new Categorie();
		categ.setId(categorie);
		Produit produit = new Produit();
		produit.setNom(nom);
		produit.setDesc(desc);
		produit.setPrix(tarif);
		produit.setVisual(visual);
		produit.setCateg(categ);
		verif = daos.getProduitDAO().create(produit);
		if (verif==true)
			System.out.println("produit ajoute avec succes");
		else  {							
			System.out.println("Echec de l'ajout du produit");
			}
		}
	
	//Suppression
	else if(action==2) {
			System.out.println("Saisissez l'id du produit a supprimer");
			int id = scI.nextInt();
			Produit produit = new Produit();
			produit.setID(id);
			verif=daos.getProduitDAO().delete(produit);
			if(verif==true) 
				System.out.println("produit supprime avec succes");
			else
				System.out.println("Echec de la suppression du produit");
		}
			
	//Modification
	else if(action==3) {
			System.out.println("Saisissez l'ID du produit a modifier");
			int id = scI.nextInt();
			System.out.println("Saisissez les nouvelles valeurs pour le nom du produit puis sa descrpition, son tarif, son visuel et enfin l'ID de sa categorie");
			String nom = scS.nextLine();
			String desc = scS.nextLine();
			double tarif = scD.nextDouble();
			String visual = scS.nextLine();
			int categorie = scI.nextInt();
			Categorie categ = new Categorie();
			categ.setId(categorie);
			Produit produit = new Produit();
			produit.setID(id);
			produit.setNom(nom);
			produit.setDesc(desc);
			produit.setPrix(tarif);
			produit.setVisual(visual);
			produit.setCateg(categ);	
			verif = daos.getProduitDAO().update(produit);
			if(verif==true) 
				System.out.println("Produit modifie avec succes");
			else 
				System.out.println("Echec dans la modification du produit");
		}
	
	//Recherche
	else if (action==4) {
	
			System.out.println("Voulez vous rechercher par ID (1), ou bien voulez vous voir la table complète (2) ?");
			int recherche = scI.nextInt();
			
			//Recherche par identifiant
			if(recherche==1) {
				System.out.println("Saisissez l'ID du produit a rechercher");
				int ID = scI.nextInt();
				Produit produit = daos.getProduitDAO().getById(ID);
				if(produit.getID() != 0)
				System.out.println("Le Produit n"+ID+" est : "+produit);
			}
			
			
			//Tout Afficher
			else if(recherche == 2) {
				List<Produit>liste = new ArrayList<>();
				liste = daos.getProduitDAO().getAll();
				for(int i = 0; i < liste.size() ; i++) {
					System.out.println(liste.get(i));
					}
			}
		}
	}
}
