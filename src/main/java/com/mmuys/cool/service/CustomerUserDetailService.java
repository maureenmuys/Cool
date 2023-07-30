package com.mmuys.cool.service;

import com.mmuys.cool.model.CustomUserDetail;
import com.mmuys.cool.model.User;
import com.mmuys.cool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException(email + " is not found "));
        return user.map(CustomUserDetail::new).get();
    }
}
