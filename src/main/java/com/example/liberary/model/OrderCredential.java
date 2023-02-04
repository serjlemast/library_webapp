package com.example.liberary.model;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCredential {
    private int id;
    private int userId;
    private String userName;
    private int librarianId;
    private int bookId;
    private String bookName;
    private String bookStatus;
    private Date createDate;
    private Date returnDate;
    private Status status;
    private boolean subscription;
    private String bookIds;
    private String bookNames;
}
