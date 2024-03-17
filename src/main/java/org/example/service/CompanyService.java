package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final UserService userService;

    @Autowired
    public CompanyService(UserService userService) {
        this.userService = userService;
    }
}
