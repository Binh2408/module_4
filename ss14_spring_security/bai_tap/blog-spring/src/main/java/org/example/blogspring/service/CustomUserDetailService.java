package org.example.blogspring.service;

import org.example.blogspring.model.User;
import org.example.blogspring.model.UserRole;
import org.example.blogspring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("==> Đã vào loadUserByUsername với username: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("Không tìm thấy username: " + username);
                    return new UsernameNotFoundException("User not found");
                });

        System.out.println("Tìm thấy user: " + user.getUsername());
        System.out.println("Password (trong DB): " + user.getPassword());

        user.getUserRoles().forEach(role -> System.out.println("ROLE = " + role.getRole().getName()));

        List<GrantedAuthority> authorities = user.getUserRoles().stream()
                .map(UserRole::getRole)
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

}
