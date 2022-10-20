package be.intecbrussel.springfrontend.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class PasswordEncoder {

    public String encode(String rawPassword){
        return rawPassword.concat("intecbrussel");
    }

    public boolean matches(@NotNull  String encodedPassword, @NotNull String rawPassword){
        return encodedPassword.equals(encode(rawPassword));
    }
}
