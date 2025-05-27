package fr.miage.lroux.accesscard.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

/**
 * Entity representing an Access Card.
 */
@Data
@Builder
@Entity
public class AccessCard {

    /**
     * Access Card ID.
     */
    @Id
    @GeneratedValue
    private long cardId;

    /**
     * Access Card password.
     */
    private int password;

    /**
     * User associated with the Access Card.
     */
    private int userId;

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
     * @param cardId  Access Card ID
     * @param password Access Card password
     * @param userId    User associated with the Access Card
     */
    public AccessCard(long cardId, int password,int userId) {
        this.cardId = cardId;
        this.password = password;
        this.userId = userId;
    }

    /**
     * Get the Access Card ID.
     *
     * @return the Access Card ID
     */
    public long getCardId() {
        return cardId;
    }


    /**
     * Get the Access Card password.
     * @return the Access Card password
     */
    public int getPassword() {
        return password;
    }

    /**
     * Set the Access Card ID.
     * @param cardId the Access Card ID
     */
    public void setCardId(long cardId) {
        this.cardId = cardId;
    }


    /**
     * Set the Access Card password.
     * @param password the Access Card password
     */
    public void setPassword(int password) {
        this.password = password;
    }
}
