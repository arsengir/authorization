package com.github.arsengir.authorization.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class User {
    @NotNull(message = "User is null")
    @NotEmpty(message = "User is empty")
    private final String login;
    @NotNull(message = "Password is null")
    @NotEmpty(message = "Password is empty")
    private final String password;
    private final List<Authorities> rules;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        rules = List.of(Authorities.READ);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Authorities> getRules() {
        return rules;
    }

}
