package eu.epitech.area.repository;

import eu.epitech.area.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
