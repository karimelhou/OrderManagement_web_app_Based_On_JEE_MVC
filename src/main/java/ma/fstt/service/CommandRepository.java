package ma.fstt.service;

import ma.fstt.entities.Commande;
import java.util.List;

public interface CommandRepository {
    void addCommand(Commande command);
    void updateCommand(Commande command);
    void deleteCommand(int commandId);
    Commande findById(int commandId);
    List<Commande> findAllCommands();
}
