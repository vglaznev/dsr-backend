package com.vglaznev.shorturlservice.service;

import com.vglaznev.shorturlservice.dto.UserDto;
import com.vglaznev.shorturlservice.entity.UserEntity;
import com.vglaznev.shorturlservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    //Only have one role in service
    private static final String ROLE = "USER_ROLE";
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User with username %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user ->
                        new User(
                                user.getUsername(),
                                user.getPassword(),
                                List.of(new SimpleGrantedAuthority(ROLE))))
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, username)));

    }

    public boolean registerUser(UserDto user) {
        boolean isExist = userRepository.findByUsername(user.getUsername()).isPresent();
        if (isExist) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.save(new UserEntity(user.getUsername(), encodedPassword));

        return true;
    }

    public boolean validateUser(UserDto user) {
        return userRepository.findByUsername(user.getUsername())
                .map(u -> passwordEncoder.matches(user.getPassword(), u.getPassword()))
                .orElse(false);
    }
}
