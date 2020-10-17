package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Client; 

public class MenuClient {
	  @SuppressWarnings("resource")
	public static void Client(DAOFactory daos, int action){
	    boolean verif;
	    Scanner scI = new Scanner(System.in);
		Scanner scS = new Scanner(System.in);
			//Ajout
			if(action==1) {
				System.out.println("Saisissez le nom puis le prenom du client a ajouter");
				String nom = scS.nextLine();
				String prenom = scS.nextLine();
				Client client = new Client(0, nom, prenom);
				verif = daos.getClientDAO().create(client);
				if(verif==true) 
					System.out.println("Client ajoute avec succes");
				else
					System.out.println("Echec dans l'ajout du client");
			}
		
			//Supression
			else if(action==2) {
				System.out.println("Saisissez l'id du client a supprimer");
				int id = scI.nextInt();
				Client client = new Client();
				client.setID(id);
				verif=daos.getClientDAO().delete(client);
				if(verif==true) 
					System.out.println("Client supprime avec succes");
				else
					System.out.println("Echec de la suppression du client");	 
			}
			
			//Modification
			else if(action==3){
				System.out.println("Saisissez l'ID du client a modifier");
				int id = scI.nextInt();
				System.out.println("Saisissez les nouvelles valeurs pour nom et prenom");
				String nom =scS.nextLine();
				String prenom = scS.nextLine();
				Client client = new Client(id,nom,prenom);
				verif = daos.getClientDAO().update(client);
				if(verif==true)
					System.out.println("Client modifie avec succes");
				if(verif==false)
					System.out.println("Echec dans la modification du client");
			}
				
			//Recherche
			else if(action==4) {
				
				System.out.println("Voulez vous rechercher par ID (1), ou bien voulez vous voir la table complète (2) ?");
				int recherche = scI.nextInt();
			
				//Recherche par identifiant
				if(recherche == 1) {
					System.out.println("Saisissez l'ID du client a rechercher");
					int ID = scI.nextInt();
					Client client = daos.getClientDAO().getById(ID);
					System.out.println("Le client n"+ID+" est : "+client);
				}
				
			//Tout Afficher
				else if(recherche==2) {
					List<Client>liste = new ArrayList<>();
					liste = daos.getClientDAO().getAll();
					for(int i = 0; i < liste.size() ; i++) {
						System.out.println(liste.get(i));
						}
					}
				}
			}
		}