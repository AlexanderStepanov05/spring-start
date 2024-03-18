package org.example.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.database.pool.ConnectionPool;
import org.example.integration.annotation.IT;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private UserService userService;
    private ConnectionPool connectionPool;

    @Test
    void test() {

    }
}
