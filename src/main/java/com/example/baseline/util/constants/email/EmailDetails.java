package com.example.baseline.util.constants.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private  String recipient;
    private String nsgBody;
    private String subject;

}
