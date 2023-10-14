package br.com.automatodev.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automatodev.dto.UserModel;
import br.com.automatodev.infrastructure.entity.UserEntity;
import br.com.automatodev.infrastructure.exception.ConflictException;
import br.com.automatodev.repository.UserRepository;
import br.com.automatodev.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired private UserRepository userRepo;

    @Autowired private ModelMapper modelMapper;

    @Override
    public UserModel postNewUser(UserModel newUserModel) {
        
        if(userRepo.findByUserName(newUserModel.getUserName()).isPresent()) {

            throw new ConflictException();
        }

        var newUserEntity = modelMapper.map(newUserModel, UserEntity.class);
        newUserModel = modelMapper.map(userRepo.save(newUserEntity), UserModel.class);

        return newUserModel;
        
    }
    
}
