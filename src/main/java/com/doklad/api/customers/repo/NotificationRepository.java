package com.doklad.api.customers.repo;

import com.doklad.api.customers.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findById(Long id);
    Notification save(Notification notification);

    @Query("SELECT n FROM User u JOIN u.notifications n WHERE u.id = :userId")
    Optional<Notification> findNotificationByUserId(@Param("userId") Long id);
}
