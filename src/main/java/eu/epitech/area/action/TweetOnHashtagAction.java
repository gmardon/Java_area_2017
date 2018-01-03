package eu.epitech.area.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import javax.persistence.Entity;
import java.util.List;
import java.util.function.Consumer;

@Entity
public class TweetOnHashtagAction extends Action {
    @Autowired
    private Twitter twitter;

    private List<Tweet> lastTweets;

    public TweetOnHashtagAction(String[] params) {
        super("TweetOnHashtagAction", params);
        if (params.length < 1)
            throw new IllegalArgumentException("need an hashtag arg");
    }

    @Override
    public void apply(Consumer<String[]> function) {
        List<Tweet> tweets = twitter.searchOperations().search("#" + params[0]).getTweets();
        if (lastTweets == null) {
            lastTweets = tweets;
            return;
        }
        tweets.removeAll(lastTweets);
        for (Tweet tweet: tweets) {
            String[] params = {tweet.getText()};
            function.accept(params);
            lastTweets.add(tweet);
        }
    }
}
