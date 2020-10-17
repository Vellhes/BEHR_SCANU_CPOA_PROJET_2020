package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daofactory.DAOFactory;
import metier.Client;
import metier.Commande;

public class MenuCommande {
	  @SuppressWarnings("resource")
	public static void Commande(DAOFactory daos, int action){
	    boolean verif;
	    Scanner scI = new Scanner(System.in);
		Scanner scS = new Scanner(System.in);
			//Ajout
			if(action==1) {
			System.out.println("saisissez la date au format : yyyy-mm-dd hh:mm:ss puis l'id du client");
			String date = scS.nextLine();
			int id_client = scI.nextInt();
			Client client = new Client();
			client.setID(id_client);
			Commande commande = new Commande();
			commande.setId(0);
			commande.setDate(date);
			commande.setClient(client);
			verif = daos.getCommandeDAO().create(commande);
			if (verif==true)
				System.out.println("Commande ajoute avec succes");
			else
				System.out.println("Echec dans l'ajout de la commande");
			}
			
			//Suppression
			else if(action==2) {
				
				System.out.println("Saisissez l'id de la commande a supprimer");
				int id = scI.nextInt();
				Commande commande = new Commande();
				commande.setId(id);
				verif=daos.getCommandeDAO().delete(commande);
				
				if(verif==true) 
					System.out.println("Commande supprimee avec succes");
				else
					System.out.println("Echec de la suppression de la commande");
			}
			
			//Modification
			else if(action==3) {
				System.out.println("Saisissez l'ID de la commande à modifier");
				int id = scI.nextInt();
				System.out.println("Saisissez la nouvelle date au format : yyyy-mm-dd et le nouvel ID de client");
				String date =scS.nextLine();
				int id_client = scI.nextInt();
				Client client = new Client();
				client.setID(id_client);
				Commande commande = new Commande();	
				commande.setId(id);
				commande.setDate(date);
				commande.setClient(client);
				verif = daos.getCommandeDAO().update(commande);
				if(verif==true)
					System.out.println("Commande modifiee avec succes");
				else 
					System.out.println("Echec dans la modification de la commande");
			}
			
			//Recherche
			else if(action==4) {
				System.out.println("Voulez vous rechercher par ID (1), ou bien voulez vous voir la table complète (2) ?");
				int recherche = scI.nextInt();
				
				//Recherche par identifiant
				if(recherche == 1) {
					
					System.out.println("Saisissez l'ID de la commande à rechercher");
					int ID = scI.nextInt();
					Commande commande = daos.getCommandeDAO().getById(ID);
					System.out.println("La Commande n"+ID+" est : "+commande);
					}
				
				//Tout Afficher
				else if(recherche == 2) {
					List<Commande>liste = new ArrayList<>();
					liste = daos.getCommandeDAO().getAll();
					for(int i = 0; i < liste.size() ; i++) {
						System.out.println(liste.get(i));
						}
					}
				}
			}
		}
