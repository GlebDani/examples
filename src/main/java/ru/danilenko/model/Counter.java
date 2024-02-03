package ru.danilenko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDate;


/**
 * Entity for counter which has user_id and time of applying, the inserted value and the type of the counter
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Counter {

    private int counterId;
    private int userId;
    private String date;
    private int counterMeasure;
    private int counterTypeId;


}
