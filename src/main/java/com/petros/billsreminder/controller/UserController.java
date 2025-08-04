package com.petros.billsreminder.controller;

import com.petros.billsreminder.dto.UserInsertDTO;
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

//    @PostMapping("/users")
//    public ResponseEntity<User> saveUser(@RequestBody UserInsertDTO dto) {
//        User user = mapper.mapDtoToUserEntity(dto);
//        User savedUser = userService.saveUser(user);
//        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
//    }


    // save userReadOnlyDto
    // getUserWithID
    //updateUser
    //deleteUser

    //getPaginatedUsers


}
