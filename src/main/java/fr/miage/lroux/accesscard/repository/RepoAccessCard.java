package fr.miage.lroux.accesscard.repository;

import fr.miage.lroux.accesscard.entity.AccessCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAccessCard extends CrudRepository<AccessCard, Long> {
}
