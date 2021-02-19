package com.company.BandiBank.Repository;

import com.company.BandiBank.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("update User u set u.name=?2, u.lastName=?3, u.email=?4, u.phone =?5 where u.id = ?1")
    void updateUser(Long id, String name, String lastName, String email, String phone);

    Optional<User> findByPhone(String phone);
}

