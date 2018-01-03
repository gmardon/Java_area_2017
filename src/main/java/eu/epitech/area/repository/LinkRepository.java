package eu.epitech.area.repository;

import eu.epitech.area.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT link FROM Link link INNER JOIN link.author author where author.id = :userId")
    public List<Link> findByAuthor(@Param("userId") long userId);
}
