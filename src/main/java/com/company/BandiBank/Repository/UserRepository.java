package com.company.BandiBank.Repository;

import com.company.BandiBank.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update User u set u.name=?2, u.lastName=?3 where u.id = ?1")
    void updateUser(Long id, String name, String lastName);
}

