package eu.epitech.area.action;

import eu.epitech.area.reaction.Reaction;
import eu.epitech.area.security.User;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubCommit;
import org.springframework.social.twitter.api.Tweet;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class GithubOnCommitAction extends Action {
    private List<GitHubCommit> lastCommits;

    public GithubOnCommitAction(String[] params) {
        super("GithubOnCommitAction", params);
        if (params.length < 2)
            throw new IllegalArgumentException("need an user and a repo argument");
    }

    @Override
    public void apply(User author, Reaction reaction) {
        GitHub github = getConnection(author, GitHub.class);
        github.repoOperations().getCommits(this.params[0], this.params[1]);
        List<GitHubCommit> commits = github.repoOperations().getCommits(this.params[0], this.params[1]);
        if (lastCommits == null) {
            lastCommits = commits;
            return;
        }
        commits.removeAll(lastCommits);
        for (GitHubCommit commit : commits) {
            String[] params = {commit.getMessage(), commit.getAuthor().getName()};
            reaction.apply(author, params);
            lastCommits.add(commit);
        }
    }
}
