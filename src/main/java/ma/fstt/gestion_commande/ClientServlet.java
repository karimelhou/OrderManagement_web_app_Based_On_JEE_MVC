package ma.fstt.gestion_commande;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientServlet", value = "/clients")
public class ClientServlet extends HttpServlet {
    @Inject
    private ClientRepository clientRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listClients(request, response);
                break;
            case "create":
                showClientForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            default:
                listClients(request, response);
        }
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientRepository.findAllClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/client-list.jsp").forward(request, response);
    }

    private void showClientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/client-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clientId = Integer.parseInt(request.getParameter("id"));
        Client client = clientRepository.findById(clientId);
        request.setAttribute("client", client);
        request.getRequestDispatcher("/client-form.jsp").forward(request, response);
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clientId = Integer.parseInt(request.getParameter("id"));
        clientRepository.deleteClient(clientId);
        response.sendRedirect(request.getContextPath() + "/clients");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                createClient(request, response);
                break;
            case "update":
                updateClient(request, response);
                break;
            default:
                listClients(request, response);
        }
    }

    private void createClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");

            // Create a new Client object
            Client newClient = new Client(1, nom, prenom, telephone, email);

            // Save the client to the database using clientRepository
            clientRepository.addClient(newClient);

            // Redirect to the client list page or show a success message
            response.sendRedirect(request.getContextPath() + "/clients?action=list");
        } catch (Exception e) {
            e.printStackTrace(); // Log the error

        }
    }


    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");

            // Create a new Client object with updated data
            Client updatedClient = new Client(clientId, nom, prenom, telephone, email);

            // Update the client in the database using clientRepository
            clientRepository.updateClient(updatedClient);

            // Redirect to the client list page or show a success message
            response.sendRedirect(request.getContextPath() + "/clients?action=list");
        } catch (Exception e) {
            e.printStackTrace(); // Log the error

        }
    }

}

