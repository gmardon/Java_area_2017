package eu.epitech.area.service;

import eu.epitech.area.link.Link;
import eu.epitech.area.security.User;

import java.util.List;

public interface LinkService {
    void create(String actionName,
                String[] actionParams,
                String reactionName,
                String[] reactionParams,
                User author);

    void create(Link link);

    List<Link> getAll();
    List<Link> getAllFromUser(User user);
}
