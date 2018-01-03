package eu.epitech.area.repository;

import eu.epitech.area.link.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("SELECT link FROM Link link where link.author.id = ?1")
    public List<Link> findByAuthor(long userId);
}
