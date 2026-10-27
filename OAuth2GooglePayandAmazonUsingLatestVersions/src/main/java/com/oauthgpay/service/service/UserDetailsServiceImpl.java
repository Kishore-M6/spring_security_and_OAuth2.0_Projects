package com.oauthgpay.service.service;


import com.oauthgpay.entities.Users;
import com.oauthgpay.repository.UsersandRolesRepository;
import com.oauthgpay.service.beans.UserDetailsImplementation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersandRolesRepository usersandRolesRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        UserDetails userDetails = null;
        users = usersandRolesRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Users entity not found with" + username);
        }
        userDetails = new UserDetailsImplementation(users.getSystemUserId(), users.getUsername(), users.getPassword(), users.getRoles().getRoleName(), users.getStatus());
        System.out.println("systemuserid: "+ users.getSystemUserId()+" username: "+users.getUsername());
        return userDetails;
    }
}
