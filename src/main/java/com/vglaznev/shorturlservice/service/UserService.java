package com.vglaznev.shorturlservice.service;

import com.vglaznev.shorturlservice.dto.RegisterRequest;
import com.vglaznev.shorturlservice.entity.UserEntity;
import com.vglaznev.shorturlservice.exception.UserAlreadyExistException;
import com.vglaznev.shorturlservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    //Only have one role in service
    private static final String ROLE = "USER_ROLE";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User with username %s not found";
    private static final String USER_EXIST_ERROR_MESSAGE = "User with username %s already exist";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user ->
                        new User(
                                user.getUsername(),
                                user.getPassword(),
                                Arrays.asList(new SimpleGrantedAuthority(ROLE))))
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, username)));

    }

    public void registerUser(RegisterRequest user) {
        boolean isExist = userRepository.findByUsername(user.username()).isPresent();

        if (isExist) {
            throw new UserAlreadyExistException(String.format(USER_EXIST_ERROR_MESSAGE, user.username()));
        }

        String encodedPassword = passwordEncoder.encode(user.password());

        userRepository.save(new UserEntity(user.username(), encodedPassword));
    }
}
