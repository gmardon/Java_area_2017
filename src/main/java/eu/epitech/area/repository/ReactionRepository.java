package eu.epitech.area.repository;

import eu.epitech.area.reaction.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}