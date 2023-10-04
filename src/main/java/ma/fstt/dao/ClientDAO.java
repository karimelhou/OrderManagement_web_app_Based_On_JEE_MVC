package ma.fstt.dao;

import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements ClientRepository {


    // Declare instance variables for the DAO
    private Connection mycnx;
    private PreparedStatement preparedStatement;
    private ResultSet resultset;

    // Constructor for initializing the connection
    ClientDAO() throws SQLException, ClassNotFoundException {
        this.mycnx = ConnectionJDBC.getInstance();
    }

    @Override
    public void addClient(Client client) {

        String request = "INSERT INTO Client (nom, prenom, telephone, email) VALUES (?, ?, ?, ?)";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            // MOR mapping object relation
            this.preparedStatement.setString(1, client.getNom());
            this.preparedStatement.setString(2, client.getPrenom());
            this.preparedStatement.setString(3, client.getTelephone());
            this.preparedStatement.setString(4, client.getEmail());

            // Log the SQL query and parameter values
            System.out.println("SQL Query: " + request);
            System.out.println("Parameters: " + client.getNom() + ", " + client.getPrenom() + ", " + client.getTelephone() + ", " + client.getEmail());

            this.preparedStatement.execute();

            // Commit the transaction if needed
            // this.mycnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
    }

    @Override
    public void updateClient(Client client) {
        String request = "UPDATE Client SET nom = ?, prenom = ?, telephone = ?, email = ? WHERE idClient = ?";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            // MOR mapping object relation
            this.preparedStatement.setString(1, client.getNom());
            this.preparedStatement.setString(2, client.getPrenom());
            this.preparedStatement.setString(3, client.getTelephone());
            this.preparedStatement.setString(4, client.getEmail());
            this.preparedStatement.setInt(5, client.getId_client());

            // Log the SQL query and parameter values
            System.out.println("SQL Query: " + request);
            System.out.println("Parameters: " + client.getNom() + ", " + client.getPrenom() + ", " + client.getTelephone() + ", " + client.getEmail() + ", " + client.getId_client());

            this.preparedStatement.executeUpdate();

            // Commit the transaction if needed
            // this.mycnx.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
    }


    @Override
    public void deleteClient(int clientId) {

        String request = "DELETE FROM Client WHERE idClient = ?";

        try{
            this.preparedStatement = this.mycnx.prepareStatement(request);
            this.preparedStatement.setInt(1, clientId);

            System.out.println("SQL Query: " + request);
            System.out.println("Parameter (id_client): " + clientId);

            this.preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Client findById(int id) {
        String request = "SELECT * FROM Client WHERE idClient = ?";

        try {
            this.preparedStatement = this.mycnx.prepareStatement(request);
            this.preparedStatement.setInt(1, id);

            ResultSet result = this.preparedStatement.executeQuery();

            if (result.next()) {
                int clientId = result.getInt("idClient");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String telephone = result.getString("telephone");
                String email = result.getString("email");

                return new Client(clientId, nom, prenom, telephone, email);
            }

            result.close();
            this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here as needed
        }
        return null; // Return null if no matching client is found
    }


    @Override
    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        String request = "SELECT * FROM Client";

        try{
            this.preparedStatement = this.mycnx.prepareStatement(request);
            ResultSet result = this.preparedStatement.executeQuery();

            while(result.next()){
                int clientId = result.getInt("idClient");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String telephone = result.getString("telephone");
                String email = result.getString("email");

                Client client = new Client(clientId,  nom,  prenom,  telephone, email);
                clients.add(client);

            }

            result.close();
            this.preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clients;

    }
}
