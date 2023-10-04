package ma.fstt.entities;

public class LigneDeCommande {
    private int idLigneDeCommande;
    private int quantite;
    private double montantLigne;

    private Commande commande;
    private Produit produit;

    public int getIdLigneDeCommande() {
        return idLigneDeCommande;
    }

    public void setIdLigneDeCommande(int idLigneDeCommande) {
        this.idLigneDeCommande = idLigneDeCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontantLigne() {
        return montantLigne;
    }

    public void setMontantLigne(double montantLigne) {
        this.montantLigne = montantLigne;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "LigneDeCommande{" +
                "idLigneDeCommande=" + idLigneDeCommande +
                ", quantite=" + quantite +
                ", montantLigne=" + montantLigne +
                ", commande=" + commande +
                ", produit=" + produit +
                '}';
    }
}
