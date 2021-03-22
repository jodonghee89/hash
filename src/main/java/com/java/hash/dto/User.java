package com.java.hash.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String id;
    private String digest;
    private String salt;

    public static User of(final String id, final String digest, final String salt) {
        return new User(id, digest, salt);
    }
}
