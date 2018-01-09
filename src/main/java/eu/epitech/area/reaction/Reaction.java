package eu.epitech.area.reaction;

import eu.epitech.area.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.twitter.api.Twitter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Formatter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Entity
@Table(name = "reaction")
public abstract class Reaction implements Serializable {
    @Id
    private Long id;
    protected String name;
    protected String[] params;

    @Autowired
    private transient UsersConnectionRepository usersConnectionRepository;

    public Reaction(String name, String[] params) {
        this.name = name;
        this.params = params;
    }

    public void apply(User user, String[] actionResultParams) {
        try {
            String[] params = this.params.clone();
            for (int i = 0; i < params.length; i++) {
                Formatter formatter = new Formatter();
                params[i] = formatter.format(params[i], actionResultParams).toString();
                formatter.close();
            }
            consumer().accept(user, params);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected <T> T getConnection(User author, Class<T> connectionClass) {
        return usersConnectionRepository.createConnectionRepository(author.getUsername()).getPrimaryConnection(connectionClass).getApi();
    }

    protected abstract BiConsumer<User, String[]> consumer();

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
