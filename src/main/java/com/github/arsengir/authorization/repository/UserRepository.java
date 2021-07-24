package com.github.arsengir.authorization.repository;

import com.github.arsengir.authorization.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, String> users = new ConcurrentHashMap<>();

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (users.containsKey(user) && users.get(user).equals(password)) {
            return Arrays.asList(Authorities.values());
        } else {
            return Collections.emptyList();
        }
    }
}
