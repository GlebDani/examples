package ru.danilenko.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity for counter type
 */
@Setter
@Getter
@AllArgsConstructor
public class CounterType {

    private int counterTypeId;
    private String description;
}
