package com.petros.billsreminder.controller;

import com.petros.billsreminder.core.exceptions.AppObjectAlreadyExists;
import com.petros.billsreminder.dto.UserInsertDTO;
import com.petros.billsreminder.dto.UserReadOnlyDTO;
import com.petros.billsreminder.mapper.Mapper;
import com.petros.billsreminder.model.User;
import com.petros.billsreminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserReadOnlyDTO> register(@RequestBody UserInsertDTO dto) throws AppObjectAlreadyExists {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    // getUserWithID
    //updateUser

    //getPaginatedUsers




}
