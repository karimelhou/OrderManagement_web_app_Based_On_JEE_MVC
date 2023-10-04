package ma.fstt.service;

import ma.fstt.entities.Client;
import java.util.List;

public interface ClientRepository {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(int clientId);
    Client findById(int clientId);
    List<Client> findAllClients();
}
