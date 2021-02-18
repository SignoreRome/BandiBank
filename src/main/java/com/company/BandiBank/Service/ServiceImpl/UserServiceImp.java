package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.User;
import com.company.BandiBank.Repository.UserRepository;
import com.company.BandiBank.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUser(Long id, User updatedUser) {
        userRepository.updateUser(id, updatedUser.getName(), updatedUser.getLastName());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return userRepository.save(user);
    }
}
