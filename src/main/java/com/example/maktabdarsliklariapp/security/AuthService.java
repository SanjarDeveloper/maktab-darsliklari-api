package com.example.maktabdarsliklariapp.security;

import com.example.maktabdarsliklariapp.entity.User;
import com.example.maktabdarsliklariapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = usersRepository.findByUsername(username);
        return byUsername.orElseThrow(() -> new UsernameNotFoundException("User topilmadi!"));
    }
}
