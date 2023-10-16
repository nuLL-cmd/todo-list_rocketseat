package br.com.automatodev.service.implementation;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.automatodev.dto.UserModel;
import br.com.automatodev.infrastructure.entity.UserEntity;
import br.com.automatodev.infrastructure.exception.ConflictException;
import br.com.automatodev.repository.UserRepository;
import br.com.automatodev.service.UserService;
import lombok.NonNull;

@Service
public class UserServiceImpl implements UserService{

    @Autowired private UserRepository userRepo;

    @Autowired private ModelMapper modelMapper;

    /* ------------------------------------------------------------------------------------------------------*/

    @Override
    public UserModel postNewUser(@NonNull UserModel newUserModel) {
        
        if(userRepo.findByUserName(newUserModel.getUserName()).isPresent()) {

            throw new ConflictException();
        }

        String passworEncrypt = BCrypt.withDefaults().hashToString(12, newUserModel.getPassword().toCharArray());
        newUserModel.setPassword(passworEncrypt);
        
        var newUserEntity = modelMapper.map(newUserModel, UserEntity.class);
        newUserModel = modelMapper.map(userRepo.save(newUserEntity), UserModel.class);

        return newUserModel;
        
    }

    /* ------------------------------------------------------------------------------------------------------*/

    @Override
    public Optional<UserModel> getUserById(@NonNull String userId) {

        return userRepo.findById(UUID.fromString(userId))
        .map(userDatabase -> Optional.of(modelMapper.map(userDatabase, UserModel.class)))
        .orElseGet(() -> Optional.ofNullable(null));

    }
    
    /* ------------------------------------------------------------------------------------------------------*/
}
