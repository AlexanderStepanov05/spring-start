package org.example.database.repository;

import org.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    @Autowired
    public UserRepository(@Qualifier("pool1") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
