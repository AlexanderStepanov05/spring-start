package org.example;

import org.example.database.ioc.Container;
import org.example.database.pool.ConnectionPool;
import org.example.database.repository.CompanyRepository;
import org.example.database.repository.UserRepository;
import org.example.service.UserService;

public class Main {
    public static void main(String[] args) {
        var container = new Container();

//        var connectionPool = new ConnectionPool();
        var connectionPool = container.get(ConnectionPool.class);
        var userRepository = container.get(UserRepository.class);
        var companyRepository = container.get(CompanyRepository.class);
        var userService = container.get(UserService.class);

    }
}