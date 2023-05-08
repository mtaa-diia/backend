package com.doklad.api.customers.repo;

import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT d FROM Document d JOIN User u ON d.user.id = u.id WHERE u.id = :userId")
    List<Document> findAllDocumentsByUserId(@Param("userId") Long id);
    long count();

}
