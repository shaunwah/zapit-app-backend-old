package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.User;
import com.shaunwah.zapitappbackend.models.UserPrincipal;
import com.shaunwah.zapitappbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user != null) {
            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException(username);
    }

//    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role: roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
}
