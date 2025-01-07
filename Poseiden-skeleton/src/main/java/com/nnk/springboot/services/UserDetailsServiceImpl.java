package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user){
        /*List<String> myRoles = new ArrayList<>();
        myRoles.add("ROLE_ADMIN");
        return myRoles.stream()
                .map(role->new SimpleGrantedAuthority("ADMIN"))
                .collect(Collectors.toList());*/

        List<GrantedAuthority> authorities = new ArrayList<>();
        //myRoles.add("ROLE_ADMIN");
        //for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(user.getFullname()));
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            /*authorities.addAll(user.getRole()
                    .stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .collect(Collectors.toList()));*/
        //}

        return authorities;

        /*Collection<User> user) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (User curseUser: user) {
                authorities.add(new SimpleGrantedAuthority(curseUser.getRole()));
                authorities.addAll(curseUser.get
                        .stream()
                        .map(p -> new SimpleGrantedAuthority(p.get()))
                        .collect(Collectors.toList()));
            }

            return authorities;
    */}

}
