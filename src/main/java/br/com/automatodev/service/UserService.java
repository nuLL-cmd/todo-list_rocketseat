package br.com.automatodev.service;

import br.com.automatodev.dto.UserModel;
import io.micrometer.common.lang.NonNull;
    
public interface UserService {
    
    public UserModel postNewUser(@NonNull UserModel newUser);
}
