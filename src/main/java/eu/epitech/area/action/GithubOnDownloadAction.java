package eu.epitech.area.action;

import eu.epitech.area.reaction.Reaction;
import eu.epitech.area.security.User;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubCommit;
import org.springframework.social.github.api.GitHubDownload;
import org.springframework.social.twitter.api.Tweet;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class GithubOnDownloadAction extends Action {
    private List<GitHubDownload> lastDownloads;

    public GithubOnDownloadAction(String[] params) {
        super("GithubOnDownloadAction", params);
        if (params.length < 2)
            throw new IllegalArgumentException("need an user and a repo argument");
    }

    @Override
    public void apply(User author, Reaction reaction) {
        GitHub github = getConnection(author, GitHub.class);
        List<GitHubDownload> downloads = github.repoOperations().getDownloads(this.params[0], this.params[1]);
        if (lastDownloads == null) {
            lastDownloads = downloads;
            return;
        }
        downloads.removeAll(lastDownloads);
        for (GitHubDownload download : downloads) {
            String[] params = {download.getName()};
            reaction.apply(author, params);
            lastDownloads.add(download);
        }
    }
}
