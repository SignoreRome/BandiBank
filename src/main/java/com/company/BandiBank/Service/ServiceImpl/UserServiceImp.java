package com.company.BandiBank.Service.ServiceImpl;

import com.company.BandiBank.Entity.User;
import com.company.BandiBank.Repository.UserRepository;
import com.company.BandiBank.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
