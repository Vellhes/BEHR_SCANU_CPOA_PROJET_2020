package menu;
import java.util.Scanner;

import daofactory.DAOFactory;
import daofactory.Persistance;


public class menu {
    private static Scanner scI;
    boolean verif;
    
    public menu() {
        scI = new Scanner(System.in);
        boolean boucle = true;
        while(boucle) {
        System.out.println("Veuillez choisir votre cible : SQL (1) , Liste Memoire (2)");
        int cible = scI.nextInt();
        System.out.println("Veuillez choisir votre table : Categorie (1), Commande (2), Client (3), Produit (4)");
        int table = scI.nextInt();
        System.out.println("Veuillez choisir votre action : Ajouter (1), Supprimer (2), Modifier (3), Rechercher (4)");
        int action = scI.nextInt();
        DAOFactory daos = null;

        if(cible==1) {
             daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
        }
        else if(cible == 2) {
                
             daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
            
            //Categorie
            if(table==1) {
                MenuCategorie.Categorie(daos,action);
            }
            //Commande
            else if(table==2) {
                MenuCommande.Commande(daos, action);
            }
            //Client
            else if(table==3) {
                MenuClient.Client(daos, action);
            }
            
            //Produit
            else if(table==4) {
                MenuProduit.Produit(daos, action);
            }
        }
    }
}