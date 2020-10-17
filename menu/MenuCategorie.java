package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Categorie;

public class MenuCategorie {
	  @SuppressWarnings("resource")
	public static void Categorie(DAOFactory daos, int action){
	    boolean verif;
	    Scanner scI = new Scanner(System.in);
		Scanner scS = new Scanner(System.in);
		
		//Ajout
		if(action==1) { 
			System.out.println("Saisissez le titre de la categorie puis le nom du fichier photo");
			String titre = scS.nextLine();
			String visuel = scS.nextLine();
			Categorie categorie = new Categorie(0,titre,visuel);
			verif = daos.getCategorieDAO().create(categorie);
			if (verif==true)
				System.out.println("Categorie ajoute avec succes");
			else  {							
				System.out.println("Echec de l'ajout de la categorie");
				}
			}
			
			//Suppression
			else if(action==2) {
				System.out.println("Saisissez l'id de la categorie a supprimer");
				int id = scI.nextInt();
				Categorie categorie = new Categorie();
				categorie.setId(id);
				verif=daos.getCategorieDAO().delete(categorie);
				if(verif==true) 
					System.out.println("Categorie supprimee avec succes");
				else
					System.out.println("Echec de la suppression de la categorie");
			}
				
			//Modification
			else if(action==3) {
				System.out.println("Saisissez l'ID de la categorie a modifier");
				int id = scI.nextInt();
				System.out.println("Saisissez les nouvelles valeurs pour le titre et ensuite le nom du de l'image");
				String titre =scS.nextLine();
				String visuel = scS.nextLine();
				Categorie categorie = new Categorie();	
				categorie.setId(id);
				categorie.setTitre(titre);
				categorie.setVisual(visuel);
				verif = daos.getCategorieDAO().update(categorie);
				if(verif==true) 
					System.out.println("Categorie modifiÃ©e avec succes");
			}
			
			//Recherche
			else if (action==4) {
				System.out.println("Voulez vous rechercher par ID (1), ou bien voulez vous voir la table complète (2) ?");
				int recherche = scI.nextInt();
			
				//Recherche par identifiant
				if(recherche == 1) {
					System.out.println("Saisissez l'ID de la categorie a rechercher");
					int ID = scI.nextInt();
					Categorie categorie = daos.getCategorieDAO().getById(ID);
					System.out.println("La categorie n"+ID+" est : "+categorie);
				}
				
				//Tout afficher
				else if(recherche == 2) {
					List<Categorie>liste = new ArrayList<>();
					liste = daos.getCategorieDAO().getAll();
					for(int i = 0; i < liste.size() ; i++) {
						System.out.println(liste.get(i));
					}
				}
			}
		}
	  }