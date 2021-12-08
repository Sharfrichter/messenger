package com.company.messenger.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.messenger.data.entity.User;
import com.company.messenger.data.repository.UserRepository;
import com.company.messenger.web.model.ConversationWebModel;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findFriends(String username) {
        return userRepository.findFriends(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addFriend(Long firstId, Long secondId) {
        userRepository.addFriend(firstId, secondId);
    }

}
