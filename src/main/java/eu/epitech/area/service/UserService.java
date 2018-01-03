package eu.epitech.area.service;

import eu.epitech.area.security.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
