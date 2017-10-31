package eu.epitech.area.reaction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reaction")
public abstract class Reaction implements Serializable {
    @Id
    private Long id;
    protected String name;

    abstract void execute(String[] args);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
