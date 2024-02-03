package ru.danilenko.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for counter type
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CounterType {

    private int counterTypeId;
    private String description;
}
