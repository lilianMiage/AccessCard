package fr.miage.lroux.accesscard.controller;

import fr.miage.lroux.accesscard.entity.AccessCard;
import fr.miage.lroux.accesscard.service.ServiceAccessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing access card operations.
 * Provides endpoints to create, retrieve, and delete access cards.
 */
@RestController
@RequestMapping("/api/accessCard/")
public class ControllerAccessCard {

    /**
     * Service for access card operations.
     * This service handles the business logic for access card management.
     */
    @Autowired
    private ServiceAccessCard serviceAccessCard;

    /**
     * Creates a new access card.
     * @param accessCard The access card to be created.
     * @return The created access card.
     * @throws Exception If an error occurs during access card creation.
     */
    @PostMapping("create")
    public AccessCard createAccessCard(@RequestBody AccessCard accessCard) throws Exception{
        return serviceAccessCard.createAccessCard(accessCard);
    }

    @GetMapping("user/{userId}")
    public AccessCard getAccessCardByUserid(@PathVariable Long userId) throws Exception {
        return serviceAccessCard.getByUserid(userId);
    }
    /**
     * Retrieves an access card by its ID.
     * @param id The ID of the access card to retrieve.
     * @return The access card with the specified ID.
     * @throws Exception If the access card does not exist.
     */
    @GetMapping("{id}")
    public AccessCard getAccessCardById(@PathVariable Long id) throws Exception {
        return serviceAccessCard.getAccessCardById(id);
    }

    /**
     * Deletes an access card by its ID.
     * @param id The ID of the access card to delete.
     * @throws Exception If the access card does not exist.
     */
    @DeleteMapping("delete/{id}")
    public void deleteAccessCardById(@PathVariable Long id) throws Exception {
        serviceAccessCard.deleteAccessCardById(id);
    }
}
