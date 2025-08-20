package com.petros.billsreminder.dto;

import com.petros.billsreminder.core.enums.Gender;
import com.petros.billsreminder.core.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record UserInsertDTO(
        @NotNull
        @Size(min = 2, max = 20)
        String username,

        @NotNull
        String email,

        @NotNull
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$")
        String password,
        Role role,

        String firstname,
        String lastname,
        Gender gender
) {}
