package eu.epitech.area.reaction;

import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;

import javax.persistence.Entity;
import java.util.function.Consumer;

@Entity
public class TweetReaction extends Reaction {
    @Autowired
    Twitter twitter;

    public TweetReaction() {
        super("TweetReaction");
    }

    @Override
    public Consumer<String[]> consumer() {
        return (params) -> {
            if (params.length > 0)
                twitter.timelineOperations().updateStatus(params[0]);
            else
                System.err.println("Missing parameter needed to tweet");
        };
    }
}
