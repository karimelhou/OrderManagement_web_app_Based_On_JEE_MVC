package ma.fstt.service;

import ma.fstt.entities.Produit;
import java.util.List;

public interface ProduitRepository {
    void addProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(int produitId);
    Produit findById(int produitId);
    List<Produit> findAllProduits();
}
