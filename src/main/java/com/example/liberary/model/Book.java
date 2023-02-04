package com.example.liberary.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Етот клас описует books_table
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private String author;
    private Date publeshedDate;
    private String info;
    private String status;
}
