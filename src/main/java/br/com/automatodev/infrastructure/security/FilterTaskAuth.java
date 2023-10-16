package br.com.automatodev.infrastructure.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.automatodev.infrastructure.entity.UserEntity;
import br.com.automatodev.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {

                    var path = request.getServletPath();
                    if(!path.equals("/tasks")) {

                            filterChain.doFilter(request, response);
                    }else {
   
                    var auth = request.getHeader("Authorization");
                    var basicAuth = auth.substring("Basic".length()).trim();
                    
                    var authDecode = Base64.getDecoder().decode(basicAuth).toString();
                    var userName = authDecode.split(":")[0];
                    var password = authDecode.split(":")[1];
                        
                    Optional<UserEntity> userDatabase = userRepo.findByUserName(userName);
                    if(userDatabase.isEmpty()) {

                        response.sendError(401);
                    }else {
                        
                        boolean passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), userDatabase.get().getPassword().toCharArray()).verified;
                        if(passwordVerify) {

                            filterChain.doFilter(request, response);

                        } else {

                            response.sendError(401);
                        }
                    }   
                        
                    }

                }catch(Exception e) {

                    response.sendError(401);
                }
    }
}
