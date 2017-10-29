package eu.epitech.area.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}