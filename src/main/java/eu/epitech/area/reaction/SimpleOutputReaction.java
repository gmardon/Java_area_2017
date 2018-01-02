package eu.epitech.area.reaction;

import javax.persistence.Entity;

@Entity
public class SimpleOutputReaction extends Reaction {
    public SimpleOutputReaction() {
        super("SimpleOutputReaction");
    }
    @Override
    void execute(String[] args) {
        if (args.length > 0)
            System.out.println("[SimpleOutputReaction] " + args[0]);
    }
}
