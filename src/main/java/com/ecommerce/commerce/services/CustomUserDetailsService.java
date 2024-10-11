package com.ecommerce.commerce.services;

import com.ecommerce.commerce.dto.RegistrationRequest;
import com.ecommerce.commerce.enums.Role;
import com.ecommerce.commerce.models.User;
import com.ecommerce.commerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public User registerUser(RegistrationRequest request) {
        // Check if the username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Username is already taken.");
        }

        // Check if the email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email is already in use.");
        }

        // Create a new user entity
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode the password

        // Assign a default role (e.g., CUSTOMER)
        newUser.setRoles(Set.of(Role.CUSTOMER));

        // Save the new user to the database
        return userRepository.save(newUser);
    }
}
