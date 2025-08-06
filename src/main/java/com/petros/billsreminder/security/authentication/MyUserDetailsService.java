package com.petros.billsreminder.security.authentication;


import com.petros.billsreminder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service                           // Marks this class as a Spring-managed service (singleton bean)
@RequiredArgsConstructor           // Lombok generates a constructor for all final fields (here: userRepo)
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;  // Repository to access User data from the database

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This method is called by Spring Security during the authentication process.
        // It tries to find a User entity by the username provided during login.

        return userRepo.findByUsername(username)    // Query the database for a user with the given username
                .orElseThrow(() ->                   // If user not found,
                        new UsernameNotFoundException(  // throw this exception which Spring Security expects,
                                "User not found with username: " + username) // providing a useful error message.
                );
    }
}

