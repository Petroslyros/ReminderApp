package com.petros.billsreminder.dto;


import com.petros.billsreminder.core.enums.ReminderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record ReminderInsertDTO(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String notes,
        ReminderType type,
        String dueDate,
        Long userId
) {}

