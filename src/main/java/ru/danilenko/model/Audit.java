package ru.danilenko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for audit notes collection
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Audit {

    private int actionId;
    private String date;
    private int userId;
    private String operation_desc;
}
