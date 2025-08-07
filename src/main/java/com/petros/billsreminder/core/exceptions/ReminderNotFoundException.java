package com.petros.billsreminder.core.exceptions;

public class ReminderNotFoundException extends AppGenericException{

    public ReminderNotFoundException(Long reminderId) {
        super("REMINDER_NOT_FOUND", "Reminder with ID" + reminderId + "not found");
    }
}
