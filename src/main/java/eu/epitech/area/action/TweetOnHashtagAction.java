package eu.epitech.area.action;

import eu.epitech.area.reaction.Reaction;
import eu.epitech.area.security.User;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class TweetOnHashtagAction extends Action {
    private List<Tweet> lastTweets;

    public TweetOnHashtagAction(String[] params) {
        super("TweetOnHashtagAction", params);
        if (params.length < 1)
            throw new IllegalArgumentException("need an hashtag arg");
    }

    @Override
    public void apply(User author, Reaction reaction) {
        Twitter twitter = getConnection(author, Twitter.class);
        List<Tweet> tweets = twitter.searchOperations().search("#" + params[0]).getTweets();
        if (lastTweets == null) {
            lastTweets = tweets;
            return;
        }
        tweets.removeAll(lastTweets);
        for (Tweet tweet : tweets) {
            String[] params = {tweet.getText(), String.valueOf(tweet.getId()), tweet.getFromUser()};
            reaction.apply(author, params);
            lastTweets.add(tweet);
        }
    }
}
