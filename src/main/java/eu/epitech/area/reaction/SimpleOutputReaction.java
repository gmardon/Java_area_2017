package eu.epitech.area.reaction;

import javax.persistence.Entity;
import java.util.function.Consumer;

@Entity
public class SimpleOutputReaction extends Reaction {
    public SimpleOutputReaction() {
        super("SimpleOutputReaction");
    }

    @Override
    public Consumer<String[]> consumer() {
        return (params) -> {
            if (params.length > 0)
                System.out.println("[SimpleOutputReaction] " + params[0]);
        };
    }
}
