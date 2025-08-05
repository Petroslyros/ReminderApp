package com.petros.billsreminder.service;

import com.petros.billsreminder.core.exceptions.AppObjectAlreadyExists;
import com.petros.billsreminder.dto.UserInsertDTO;
import com.petros.billsreminder.dto.UserReadOnlyDTO;
import com.petros.billsreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    public UserReadOnlyDTO registerUser(UserInsertDTO dto) throws AppObjectAlreadyExists;
}
