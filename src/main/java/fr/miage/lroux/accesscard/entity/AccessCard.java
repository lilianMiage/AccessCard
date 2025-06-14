package fr.miage.lroux.accesscard.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

/**
 * Entity representing an Access Card.
 */
@Entity
public class AccessCard {

    /**
     * Access Card ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accessCardId;

    /**
     * Access Card password.
     */
    private int password;

    /**
     * User associated with the Access Card.
     */
    private long userId;

    /**
     * Default constructor for JPA.
     */
    public AccessCard() {
    }

    /**
     * Constructor for AccessCard.
     *
     * @param password Access Card password
     */
    public AccessCard(int password) {
        this.password = password;
    }

    /**
     * Constructor for AccessCard with cardId, password and user.
     *
     * @param accessCardId  Access Card ID
     * @param password Access Card password
     * @param userId    User associated with the Access Card
     */
    public AccessCard(long accessCardId, int password,long userId) {
        this.accessCardId = accessCardId;
        this.password = password;
        this.userId = userId;
    }

    /**
     * Get the Access Card ID.
     * @return the Access Card ID
     */
    public long getAccessCardId() {
        return accessCardId;
    }

    /**
     * Set the Access Card ID.
     * @param accessCardId the Access Card ID
     */
    public void setAccessCardId(long accessCardId) {
        this.accessCardId = accessCardId;
    }

    /**
     * Get the User ID associated with the Access Card.
     * @return the User ID
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Set the User ID associated with the Access Card.
     * @param userId the User ID
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Get the Access Card password.
     * @return the Access Card password
     */
    public int getPassword() {
        return password;
    }



    /**
     * Set the Access Card password.
     * @param password the Access Card password
     */
    public void setPassword(int password) {
        this.password = password;
    }
}
