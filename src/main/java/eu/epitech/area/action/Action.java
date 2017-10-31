package eu.epitech.area.action;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "action")
public abstract class Action implements Serializable {
    @Id
    private Long id;
    protected String name;
    protected String[] params;
    abstract boolean check();

    public Action(String name, String[] params) {
        this.name = name;
        this.params = params;
    }

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

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
