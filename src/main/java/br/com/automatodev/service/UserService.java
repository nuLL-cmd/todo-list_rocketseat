package br.com.automatodev.service;

import java.util.Optional;

import br.com.automatodev.dto.UserModel;
import lombok.NonNull;
    
public interface UserService {
    
    public UserModel postNewUser(@NonNull UserModel newUser);

    public Optional<UserModel> getUserById(@NonNull String userId);
}
