package ma.fstt.dao;

import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.service.CommandRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommandeDAO implements CommandRepository {

    // Declare instance variables for the DAO
    private Connection mycnx;
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private ClientDAO clientDAO;


    // Constructor for initializing the connection
    public CommandeDAO() throws SQLException, ClassNotFoundException {
        this.mycnx = ConnectionJDBC.getInstance();
        this.clientDAO = new ClientDAO();

    }

    @Override
    public void addCommand(Commande command) {
        String request = "INSERT INTO Commande (dateCommande, idClient) VALUES (?, ?)";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            // MOR mapping object relation
            this.preparedStatement.setDate(1, new java.sql.Date(command.getDate_commande().getTime()));
            this.preparedStatement.setInt(2, command.getClient().getId_client());

            // Log the SQL query and parameter values
            System.out.println("SQL Query: " + request);
            System.out.println("Parameters: " + command.getDate_commande() + ", " + command.getClient().getId_client());

            this.preparedStatement.execute();

            // Commit the transaction if needed
            // this.mycnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
    }

    @Override
    public void updateCommand(Commande command) {
        String request = "UPDATE Commande SET dateCommande = ?, idClient = ? WHERE idCommande = ?";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            // MOR mapping object relation
            this.preparedStatement.setDate(1, new java.sql.Date(command.getDate_commande().getTime()));
            this.preparedStatement.setInt(2, command.getClient().getId_client());
            this.preparedStatement.setInt(3, command.getId_commande());

            // Log the SQL query and parameter values
            System.out.println("SQL Query: " + request);
            System.out.println("Parameters: " + command.getDate_commande() + ", " + command.getClient().getId_client() + ", " + command.getId_commande());

            this.preparedStatement.executeUpdate();

            // Commit the transaction if needed
            // this.mycnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
    }

    @Override
    public void deleteCommand(int commandId) {
        String request = "DELETE FROM Commande WHERE idCommande = ?";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            this.preparedStatement.setInt(1, commandId);

            System.out.println("SQL Query: " + request);
            System.out.println("Parameter (idCommande): " + commandId);

            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
    }

    @Override
    public Commande findById(int commandId) {
        String request = "SELECT * FROM Commande WHERE idCommande = ?";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            this.preparedStatement.setInt(1, commandId);

            ResultSet result = this.preparedStatement.executeQuery();

            if (result.next()) {
                int retrievedCommandId = result.getInt("idCommande"); // Rename the variable
                java.sql.Date dateCommande = result.getDate("dateCommande");
                int clientId = result.getInt("idClient");

                // Retrieve the related Client object using the ClientDAO
                Client client = clientDAO.findById(clientId);

                // Construct a Commande object
                Commande commande = new Commande(retrievedCommandId, new java.util.Date(dateCommande.getTime()), client);
                return commande;
            }

            result.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the command with the given ID is not found
    }




    @Override
    public List<Commande> findAllCommands() {
        List<Commande> commandes = new ArrayList<>();
        String request = "SELECT * FROM Commande";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            ResultSet result = this.preparedStatement.executeQuery();

            while (result.next()) {
                int commandId = result.getInt("idCommande");
                java.sql.Date dateCommande = result.getDate("dateCommande");
                int clientId = result.getInt("idClient");

                // Retrieve the related Client object using the ClientDAO
                Client client = clientDAO.findById(clientId);

                // Construct a Commande object
                Commande commande = new Commande(commandId, new java.util.Date(dateCommande.getTime()), client);
                commandes.add(commande);
            }

            result.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

}
