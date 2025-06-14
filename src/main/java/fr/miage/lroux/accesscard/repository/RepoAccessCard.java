package fr.miage.lroux.accesscard.repository;

import fr.miage.lroux.accesscard.entity.AccessCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoAccessCard extends CrudRepository<AccessCard, Long> {

    /**
     * Finds an AccessCard by the user ID.
     *
     * @param userId the ID of the user
     * @return an Optional containing the AccessCard if found, or empty if not found
     */
    Optional<AccessCard> findByUserId(Long userId);
}
