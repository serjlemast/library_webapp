package com.example.liberary.model;

import lombok.*;

/**
 * Етот клас описует users_table
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private boolean blocked;
}
