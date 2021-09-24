package com.github.arsengir.authorization.service;

import com.github.arsengir.authorization.exception.UnauthorizedUser;
import com.github.arsengir.authorization.model.Authorities;
import com.github.arsengir.authorization.model.User;
import com.github.arsengir.authorization.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user.getLogin());
        }
        return userAuthorities;
    }

    private boolean isEmpty(List<Authorities> str) {
        return str == null || str.isEmpty();
    }

}
