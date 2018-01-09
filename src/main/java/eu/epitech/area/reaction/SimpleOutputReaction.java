package eu.epitech.area.reaction;

import eu.epitech.area.security.User;

import javax.persistence.Entity;
import java.util.function.BiConsumer;

@Entity
public class SimpleOutputReaction extends Reaction {
    public SimpleOutputReaction(String[] params) {
        super("SimpleOutputReaction", params);
    }

    @Override
    public BiConsumer<User, String[]> consumer() {
        return (user, params) -> {
            if (params.length > 0)
                System.out.println("[SimpleOutputReaction] " + params[0]);
        };
    }
}
