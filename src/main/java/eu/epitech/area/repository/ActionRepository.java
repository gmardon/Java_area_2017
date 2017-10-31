package eu.epitech.area.repository;

import eu.epitech.area.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}