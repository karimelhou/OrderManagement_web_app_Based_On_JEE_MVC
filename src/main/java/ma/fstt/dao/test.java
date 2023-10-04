package ma.fstt.dao;

import ma.fstt.entities.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Client cl = new Client(2, "elhoumaini12d3", "karim1d123", "+212675387857", "karim.elhou@gmail.com" );
        ClientDAO cld = new ClientDAO();

        //cld.addClient(cl);
        //cld.deleteClient(4);


        // Call the getAll method to retrieve all clients
        //List<Client> clients = cld.findAllClients();

        // Print the retrieved clients to the console
        /*for (Client client : clients) {
            System.out.println(client);
        }*/

        // Define the updated data
        //Client updatedClientData = new Client(3, "Updated", "UpdatedPrenom", "+123456789", "updated.email@example.com");

        // Call the updateClient method with the updated data
        //cld.updateClient(updatedClientData);

        // Print a message to indicate the update was successful
       // System.out.println("Client updated successfully!");

        // Define the client ID to search for
        //int clientIdToFind = 1; // Replace with the actual client ID to find

        // Call the findById method
        //Client foundClient = cld.findById(clientIdToFind);

        // Print the retrieved client to the console
        /* if (foundClient != null) {
            System.out.println("Found Client: " + foundClient);
        } else {
            System.out.println("Client not found!");
        }
        */
        Client clm = cld.findById(3);
        Date date_commande = new Date(120, 1, 20); // Year: 2005, Month: February (0-based), Day: 12

        //Commande cm = new Commande(1, date_commande, clm);

        CommandeDAO cmc = new CommandeDAO();

        //cmc.addCommand(cm);

        //cmc.deleteCommand(4);

        List<Commande> commandeList = cmc.findAllCommands();

        // Print the retrieved clients to the console
        for (Commande commande : commandeList) {
            System.out.println(commandeList);
        }

        Commande existingCommande = cmc.findById(2);
        Commande cmt = new Commande(5, date_commande, clm);

        existingCommande.setDate_commande(cmt.getDate_commande());
        existingCommande.setClient(cmt.getClient());
        //cmc.updateCommand(existingCommande);



        Commande foundCommande = cmc.findById(2);

        // Print the retrieved client to the console
        if (foundCommande != null) {
            System.out.println("Found Commande: " + foundCommande);
        } else {
            System.out.println("Commande not found!");
        }






    }
}