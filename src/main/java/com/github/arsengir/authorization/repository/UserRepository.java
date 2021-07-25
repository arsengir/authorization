package com.github.arsengir.authorization.repository;

import com.github.arsengir.authorization.model.Authorities;
import com.github.arsengir.authorization.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public List<Authorities> getUserAuthorities(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        if (users.containsKey(login)) {
            User userAuthorities = users.get(login);
            if (userAuthorities.getPassword().equals(password)) {
                return userAuthorities.getRules();
            }
        }

        return Collections.emptyList();
    }
}
