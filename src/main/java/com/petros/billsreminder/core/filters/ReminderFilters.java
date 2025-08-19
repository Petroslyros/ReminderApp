package com.petros.billsreminder.core.filters;

import com.petros.billsreminder.core.enums.ReminderType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReminderFilters extends GenericFilters{

    private ReminderType type;
    private String title;
}
