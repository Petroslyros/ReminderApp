package com.petros.billsreminder.dto;


import com.petros.billsreminder.core.enums.ReminderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReminderInsertDTO {

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;
        private String notes;
        private ReminderType type;
        private String dueDate;
        private Long userId; // so we can link to the user
    }

