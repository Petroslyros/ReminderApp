package com.petros.billsreminder.service;

import com.petros.billsreminder.dto.UserInsertDTO;
import com.petros.billsreminder.dto.UserReadOnlyDTO;
import com.petros.billsreminder.mapper.Mapper;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final Mapper mapper;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public UserReadOnlyDTO saveUser(UserInsertDTO dto) {
        return null;
    }
}
