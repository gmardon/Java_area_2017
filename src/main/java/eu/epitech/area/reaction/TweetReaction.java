package eu.epitech.area.reaction;

import eu.epitech.area.security.User;
import org.springframework.social.twitter.api.Twitter;

import javax.persistence.Entity;
import java.util.function.BiConsumer;

@Entity
public class TweetReaction extends Reaction {
    public TweetReaction(String[] params) {
        super("TweetReaction", params);
    }

    @Override
    public BiConsumer<User, String[]> consumer() {
        return (author, params) -> {
            if (params.length > 0)
            {
                Twitter twitter = getConnection(author, Twitter.class);
                if (twitter == null)
                    System.err.println("User not connected to twitter");
                twitter.timelineOperations().updateStatus(params[0]);
            }
            else
                System.err.println("Missing parameter needed to tweet");
        };
    }
}
