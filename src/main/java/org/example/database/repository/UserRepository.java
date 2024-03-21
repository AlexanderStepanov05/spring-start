package org.example.database.repository;

import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.dto.PersonalInfoInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer>,
        QuerydslPredicateExecutor<User> {

    @Query("select u from User u " +
    "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "select u.* from users u where u.username = :username;",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    Optional<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);


    // Collection, Stream
    // Streamable, Slice, Page

//    @EntityGraph("User.company")
//  @Lock, @QueryHints
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> tClass);

    @Query(value = "select firstname, lastname, birth_date " +
            "from users where company_id = :companyId;",
            nativeQuery = true)
    List<PersonalInfoInterface> findAllByCompanyId(Integer companyId);

}
