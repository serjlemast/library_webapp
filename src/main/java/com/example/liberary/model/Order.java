package com.example.liberary.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int userId;
    private int librarianId;
    private int bookId;
    private Long createDate;
    private String status;
    private boolean subscription;
}
