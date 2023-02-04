package com.example.liberary.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Етот клас описует таблицу ролей и основние роли для пользователя
 */
public enum Role {
    ADMIN, CLIENT, LIBRARIAN;

    public static Role getRole(Object sessionRole) {
//        Role sessionRoleIsNull = Optional.of(sessionRole)
//                .filter(Objects::nonNull) //
//                .filter(role -> !(role instanceof String)) //
//                .map(role -> (String) role) //
//                .map(role -> {
//                    //todo
//                    Optional<Role> first = Arrays.stream(Role.values()).filter(r -> r.name().equalsIgnoreCase(role))
//                            .findFirst();
//                    return first;
//                }) //
//                .filter(Optional::isPresent)
//                .get() //
//                .orElseThrow(() -> new IllegalArgumentException("sessionRole is null")); //

        if (!(sessionRole instanceof String)) {
            throw new IllegalArgumentException("sessionRole is null");
        }
        String role = (String) sessionRole;
        for (Role b : Role.values()) {
            if (b.name().equalsIgnoreCase(role)) {
                return b;
            }
        }
        throw new IllegalArgumentException("role not found");
    }

    public static String getUrl(Role role) {
        return Optional.of(role)
                .filter(Objects::nonNull)
                .map(role1 -> {
                    Optional<String> s = Arrays.stream(Role.values()).filter(r -> r.equals(role))
                            .findFirst()
                            .map(f -> "/" + f.toString().toLowerCase());
                    return s;
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse("/");
    }

}
