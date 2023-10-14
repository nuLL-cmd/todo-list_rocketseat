package br.com.automatodev.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.automatodev.dto.ReturnMessage;
import br.com.automatodev.dto.UserModel;
import br.com.automatodev.infrastructure.exception.ConflictException;
import br.com.automatodev.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReturnMessage> create(@RequestBody UserModel user) {

        try {
             
            user = userService.postNewUser(user);
            return new ResponseEntity<>(new ReturnMessage(HttpStatus.CREATED.toString(), HttpStatus.CREATED, user), HttpStatus.CREATED);

        } catch (Exception e) {
           
            if(e instanceof ConflictException) {

                return new ResponseEntity<>(
                    new ReturnMessage(e.getMessage(), HttpStatus.CONFLICT, user)
                    , HttpStatus.CONFLICT);
            }

             return new ResponseEntity<>(
                    new ReturnMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, user)
                    , HttpStatus.CONFLICT);
        }
    }
}
