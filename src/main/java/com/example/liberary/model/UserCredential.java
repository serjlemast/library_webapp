package com.example.liberary.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
        private int id;
        private String firstName;
        private String secondName;
        private boolean blocked;
        private String username;
        private String password;
        private String fullName;
        private String roleName;
        private Role role;
}
