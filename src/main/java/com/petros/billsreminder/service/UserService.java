package com.petros.billsreminder.service;

import com.petros.billsreminder.core.enums.Role;
import com.petros.billsreminder.core.exceptions.AppObjectAlreadyExists;
import com.petros.billsreminder.core.exceptions.UserNotFoundException;
import com.petros.billsreminder.dto.UserInsertDTO;
import com.petros.billsreminder.dto.UserReadOnlyDTO;
import com.petros.billsreminder.mapper.Mapper;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserReadOnlyDTO registerUser(UserInsertDTO dto) throws AppObjectAlreadyExists {
        if(userRepository.findByEmail(dto.email()).isPresent()) {
            throw new AppObjectAlreadyExists("Email","Email already in use");
        }

        User user = mapper.mapDtoToUserEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.BASIC_USER);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        return mapper.mapUserEntityToReadOnlyDTO(savedUser);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }


}
