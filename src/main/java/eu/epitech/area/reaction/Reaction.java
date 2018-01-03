package eu.epitech.area.reaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Formatter;
import java.util.function.Consumer;

@Entity
@Table(name = "reaction")
public abstract class Reaction implements Serializable {
    @Id
    private Long id;
    protected String name;
    protected String[] params;

    public Reaction(String name) {
        this.name = name;
    }

    public void apply(String[] actionResultParams) {
        String[] params = this.params.clone();
        for (int i = 0; i < params.length; i++) {
            Formatter formatter = new Formatter();
            params[i] = formatter.format(params[i], actionResultParams).toString();
            formatter.close();
        }
        consumer().accept(params);
    }

    protected abstract Consumer<String[]> consumer();

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
