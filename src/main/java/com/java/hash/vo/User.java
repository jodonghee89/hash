package com.java.hash.vo;

import com.java.hash.repository.Storage;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
public class User {
    private static final int SALT_SIZE = 16;
    private static Storage storage;

    static {
        storage = new Storage();
    }

    // 새로운 계정 만들기
    public void create(final String id, final byte[] digest) {
        final String salt = getSALT();
        storage.add(id,hash(digest, salt), salt);
    }

    public void get() {
        log.info("storage => {}", storage);
    }

    public void get(final String id, final byte[] pwd) {
        if (storage.check(id, hash(pwd, storage.getSaltForId(id)))) {
            log.info("login success");
        } else {
            log.info("login fail");
        }
    }

    private String hash(byte[] pwd, final String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.debug("throw error => {}", e.getMessage());
        }

        for(int i = 0; i < 300; i++) {
            md.update((byteToString(pwd) + salt).getBytes());
            pwd = md.digest();
        }

        return byteToString(pwd);
    }

    private String getSALT() {
        final byte[] temp = new byte[SALT_SIZE];
        new SecureRandom().nextBytes(temp);
        return byteToString(temp);
    }
    private String byteToString(final byte[] temp) {
        final StringBuilder sb = new StringBuilder();
        for(final byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
}
