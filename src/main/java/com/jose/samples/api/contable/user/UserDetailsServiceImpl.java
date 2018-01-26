/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.samples.api.contable.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 *
 * @author jose
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private ApplicationUserRepository applicationUserRepository;
    
    public UserDetailsServiceImpl (ApplicationUserRepository applicationUserRepository) {
        
        this.applicationUserRepository = applicationUserRepository;
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
        
    }
    
}
