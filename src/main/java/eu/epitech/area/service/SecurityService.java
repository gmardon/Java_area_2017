package eu.epitech.area.service;

import eu.epitech.area.security.User;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);

    User getLoggedUser();
}