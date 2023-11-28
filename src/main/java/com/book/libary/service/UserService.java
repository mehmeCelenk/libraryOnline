package com.book.libary.service;

import com.book.libary.model.entity.AuthorizationToken;
import com.book.libary.model.entity.User;
import com.book.libary.model.request.CreateUserRequest;
import com.book.libary.model.response.LoginResponse;
import com.book.libary.model.response.UserResponse;
import com.book.libary.repository.UserRepository;
import com.book.libary.util.Constants;
import com.book.libary.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final TokenService tokenService;


    @Cacheable("userCache")
    public LoginResponse loginResponseByCredentialsOrFail(final String email, final String password){
        final Optional<User> optionalUser = userRepository.getByEmailAndPassword(email,password);
        final AtomicReference<LoginResponse> loginResponse = new AtomicReference<>();

        optionalUser.ifPresentOrElse(user -> {
            final AuthorizationToken token = tokenService.generateToken(user);
            loginResponse.set(LoginResponse.builder().authToken(token.getToken()).build());
        }, () ->{
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return loginResponse.get();
    }

    public UserResponse createUser(final CreateUserRequest createUserRequest){
        final User user = new User();
        validateCreateUserRequestOrFail(createUserRequest);

        user.setEmail(createUserRequest.getEmail());
        user.setPassword(PasswordUtils.encode(createUserRequest.getPassword()));
        user.setRoles(Set.of(Constants.ROLE_USER));
        userRepository.save(user);
     return new UserResponse();
    }

    private void validateCreateUserRequestOrFail(final CreateUserRequest createUserRequest){
        if(CollectionUtils.isEmpty(createUserRequest.getRole())){
            throw new RuntimeException();
        }
    }


    /*public User convert(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }*/

    public Optional<User> getUser(final Long userId){
        return userRepository.findById(userId);
    }
}
