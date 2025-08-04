package com.petros.billsreminder.dto;

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
public class ReminderReadOnlyDTO {
    private Long id;
    private String title;
    private String dueDate;
    private String notes;
}
