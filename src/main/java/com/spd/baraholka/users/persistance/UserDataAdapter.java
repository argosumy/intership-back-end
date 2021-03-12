package com.spd.baraholka.users.persistance;

import org.springframework.stereotype.Service;

@Service
public class UserDataAdapter implements PersistenceUserService {

    private final UserRepository userRepository;

    public UserDataAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id){
        return userRepository.selectUserById(id);
    }
}
