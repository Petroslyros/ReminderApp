package com.petros.billsreminder.core.filters;

import com.petros.billsreminder.core.enums.ReminderType;
import com.petros.billsreminder.model.Reminder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * Utility class used to build dynamic JPA Specifications for filtering Reminder entities.
 */
public class ReminderSpecification {

    // Private constructor to prevent instantiation
    private ReminderSpecification() {}

    /**
     * Filter reminders by type.
     * Returns always-true condition if type is null or blank.
     */
    public static Specification<Reminder> typeIs(ReminderType type) {
        return (root, query, builder) -> {
            if (type == null) {
                return builder.isTrue(builder.literal(true)); // Always true if no filter
            }
            return builder.equal(root.get("type"), type); // Compare directly to enum
        };
    }


    /**
     * Filter reminders by title using LIKE (case-insensitive).
     * Returns always-true condition if title is null or blank.
     */
    public static Specification<Reminder> titleLike(String title) {
        return (root, query, builder) -> {
            if (title == null || title.trim().isEmpty()) {
                return builder.isTrue(builder.literal(true)); // Always true condition
            }
            return builder.like(
                    builder.upper(root.get("title")),
                    "%" + title.toUpperCase() + "%"
            );
        };
    }
}
