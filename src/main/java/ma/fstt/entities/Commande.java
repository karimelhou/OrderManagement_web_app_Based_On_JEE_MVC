package ma.fstt.entities;

import java.util.Date;
import java.util.List;

public class Commande {

    private int id_commande;
    private Date date_commande;

    private Client client;
    private List<LigneDeCommande> lignesDeCommande;

    public Commande(int id_commande, Date date_commande, Client client) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.client = client;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> lignesDeCommande) {
        this.lignesDeCommande = lignesDeCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", date_commande=" + date_commande +
                ", client=" + client +
                ", lignesDeCommande=" + lignesDeCommande +
                '}';
    }
}

