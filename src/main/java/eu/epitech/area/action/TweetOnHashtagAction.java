package eu.epitech.area.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import java.util.List;

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
    boolean check() {
        List<Tweet> tweets = twitter.searchOperations().search("#" + params[0]).getTweets();
        if (lastTweets == null) {
            lastTweets = tweets;
            return false;
        }
        return !(lastTweets.equals(tweets));
    }
}
