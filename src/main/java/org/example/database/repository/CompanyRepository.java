package org.example.database.repository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.bpp.Transaction;
import org.example.database.entity.Company;
import org.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Repository
@Transaction
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool;

    @Autowired
    public CompanyRepository(@Qualifier("pool2") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @PostConstruct
    private void init() {
        log.info("Init CompanyRepository...");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("Find by id method...");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("Delete method...");
    }
}
