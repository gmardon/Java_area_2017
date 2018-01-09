package eu.epitech.area.action;

import eu.epitech.area.reaction.Reaction;
import eu.epitech.area.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

@Entity
@Table(name = "action")
public abstract class Action implements Serializable {
    @Id
    private Long id;
    protected String name;
    protected String[] params;
    public abstract void apply(User author, Reaction reaction);

    @Autowired
    private transient UsersConnectionRepository usersConnectionRepository;

    public Action(String name, String[] params) {
        this.name = name;
        this.params = params;
    }

    protected <T> T getConnection(User author, Class<T> connectionClass) {
        return usersConnectionRepository.createConnectionRepository(author.getUsername()).getPrimaryConnection(connectionClass).getApi();
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
