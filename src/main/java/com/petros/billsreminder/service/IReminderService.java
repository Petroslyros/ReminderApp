package com.petros.billsreminder.service;

import com.petros.billsreminder.dto.ReminderInsertDTO;
import com.petros.billsreminder.dto.ReminderReadOnlyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReminderService {

    ReminderReadOnlyDTO createReminder(ReminderInsertDTO dto);
    List<ReminderReadOnlyDTO> getAllReminders();
}
