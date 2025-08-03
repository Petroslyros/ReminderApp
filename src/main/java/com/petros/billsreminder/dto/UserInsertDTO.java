package com.petros.billsreminder.dto;

import com.petros.billsreminder.core.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

        @NotNull
        @Size(min = 2, max = 20, message = "The username must be between 2 and 20 characters")
        private String username;

        @NotNull
        private String email;

        @NotNull(message = "The password cannot be null")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$",
                message = "Το password πρέπει να περιέχει τουλάχιστον 1 πεζό, 1 κεφαλαίο, 1 ψηφίο και 1 ειδικό χαρακτήρα χωρίς κενά.")
        private String password;
        private String firstname;
        private String lastname;
        private Gender gender;
}
