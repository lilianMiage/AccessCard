package fr.miage.lroux.accesscard.service;

import fr.miage.lroux.accesscard.entity.AccessCard;
import fr.miage.lroux.accesscard.repository.RepoAccessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing access cards.
 * Provides methods to create, retrieve, and delete access cards.
 */
@Service
public class ServiceAccessCard {

    /**
     * Repository for access card operations.
     * This repository handles the data access for access cards.
     */
    @Autowired
    private RepoAccessCard repoAccessCard;

    /**
     * Creates a new access card.
     * @param accessCard The access card to be created.
     * @return The created access card.
     * @throws IllegalArgumentException If an access card with the same ID already exists.
     */
    public AccessCard createAccessCard(AccessCard accessCard) throws IllegalArgumentException {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(accessCard.getCardId());
        if (accessCardOptional.isPresent()){
            throw new IllegalArgumentException("An access card with this ID already exist" + accessCard.getCardId());
        }
        repoAccessCard.save(accessCard);
        return accessCard;
    }

    /**
     * Retrieves an access card by its ID.
     * @param id The ID of the access card to retrieve.
     * @return The access card with the specified ID.
     * @throws Exception If no access card exists with the given ID.
     */
    public AccessCard getAccessCardById(Long id) throws Exception {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(id);
        if (accessCardOptional.isEmpty()){
            throw new Exception("No access card exist with this ID " + id);
        }
        return accessCardOptional.get();
    }

    /**
     * Deletes an access card by its ID.
     * @param id The ID of the access card to delete.
     * @throws Exception If no access card exists with the given ID.
     */
    public void deleteAccessCardById(Long id) throws Exception {
        Optional<AccessCard> accessCardOptional = repoAccessCard.findById(id);
        if (accessCardOptional.isEmpty()){
            throw new Exception("No access card exist with this ID " + id);
        }
        repoAccessCard.deleteById(id);
    }
}
