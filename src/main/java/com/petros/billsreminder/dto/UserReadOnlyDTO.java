package com.petros.billsreminder.dto;

import com.petros.billsreminder.core.enums.Gender;
import com.petros.billsreminder.core.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
