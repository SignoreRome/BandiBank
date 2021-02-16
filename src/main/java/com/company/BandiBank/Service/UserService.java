package com.company.BandiBank.Service;

import com.company.BandiBank.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> index();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    void updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    User save(User user);
}
