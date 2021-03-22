package com.java.hash.repository;

import com.java.hash.dto.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Storage {
    private static List<User> users = new ArrayList<>();
    private static final String DELIMITER = "::";

    public boolean add(final String id, final String digest, final String salt) {
        if (valid(id, digest, salt)) {
            return false;
        }
        users.add(User.of(id,digest,salt));
        log.info("{}", users.size());
        return true;
    }

    public boolean check(final String id, final String pwd) {
        if (valid(id, pwd)) {
            return false;
        }

        return users.stream().map(user -> user.getId() + DELIMITER + user.getDigest()).collect(Collectors.toCollection(HashSet::new))
                .contains(id + DELIMITER + pwd);
    }

    public String getSaltForId(final String id) {
        return users.stream().filter(user -> user.getId().equals(id)).map(User::getSalt).findFirst().orElse(null);
    }

    private boolean valid(final String... args) {
        return Arrays.stream(args).anyMatch(StringUtils::isBlank);
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ");
        users.forEach(user -> sj.add(user.getId()).add(user.getDigest()).add(user.getSalt()).add("\n"));
        return sj.toString();
    }
}