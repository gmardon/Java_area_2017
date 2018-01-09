package eu.epitech.area.service;

import eu.epitech.area.action.GithubOnCommitAction;
import eu.epitech.area.action.GithubOnDownloadAction;
import eu.epitech.area.link.Link;
import eu.epitech.area.reaction.TweetReaction;
import eu.epitech.area.security.User;
import eu.epitech.area.action.Action;
import eu.epitech.area.action.TweetOnHashtagAction;
import eu.epitech.area.reaction.Reaction;
import eu.epitech.area.reaction.SimpleOutputReaction;
import eu.epitech.area.repository.ActionRepository;
import eu.epitech.area.repository.LinkRepository;
import eu.epitech.area.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public void create(String actionName, String[] actionParams, String reactionName, String[] reactionParams, User author) {
        Link link = new Link();
        Action action = null;
        switch (actionName) {
            case "TweetOnHashtagAction":
                action = new TweetOnHashtagAction(actionParams);
                break;

            case "GithubOnCommitAction":
                action = new GithubOnCommitAction(actionParams);
                break;

            case "GithubOnDownloadAction":
                action = new GithubOnDownloadAction(actionParams);
                break;
        }
        actionRepository.save(action);
        link.setAction(action);
        Reaction reaction = null;
        switch (reactionName) {
            case "SimpleOutputReaction":
                reaction = new SimpleOutputReaction(reactionParams);
                break;

            case "TweetReaction":
                reaction = new TweetReaction(reactionParams);
                break;
        }
        reactionRepository.save(reaction);
        link.setReaction(reaction);
        link.setAuthor(author);
        this.create(link);
    }

    @Override
    public void create(Link link) {
        linkRepository.save(link);
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public List<Link> getAllFromUser(User user) {
        return linkRepository.findByAuthor(user.getId());
    }
}
