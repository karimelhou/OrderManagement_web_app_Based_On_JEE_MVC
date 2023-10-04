package ma.fstt.service;

import ma.fstt.entities.LigneDeCommande;
import java.util.List;

public interface LineCommandeRepository {
    void addLineCommande(LigneDeCommande lineCommande);
    void updateLineCommande(LigneDeCommande lineCommande);
    void deleteLineCommande(int lineCommandeId);
    LigneDeCommande findById(int lineCommandeId);
    List<LigneDeCommande> findAllLineCommandes();
}
