package com.petros.billsreminder.security.authentication;

import com.petros.billsreminder.dto.AuthenticationRequestDTO;
import com.petros.billsreminder.dto.AuthenticationResponseDTO;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service (a singleton bean for business logic)
@RequiredArgsConstructor // Lombok annotation: creates a constructor with all final fields
public class AuthenticationService {

    private final JWTService jwtService; // Generates and validates JWT tokens
    private final UserRepo userRepository; // Used to look up users (though not directly used here)
    private final AuthenticationManager authenticationManager; // Authenticates credentials (username + password)

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) {
        // This performs the actual authentication step.
        // If the credentials are wrong, an exception is thrown here.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        // Gets the authenticated user (Spring Security stores it as the principal)
        User user = (User) authentication.getPrincipal();

        // Generates a JWT for the authenticated user, including the user's role
        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());

        // Return user's info and token in a DTO to the frontend
        return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(), token);
    }
}

