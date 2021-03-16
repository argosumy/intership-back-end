package com.spd.baraholka.user.persistance;

import org.springframework.stereotype.Service;

@Service
public class UserDataAdapter implements PersistenceUserService {

    private final UserRepository userRepository;

    public UserDataAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.selectUserById(id);
    }

    @Override
    public int changeUserBlockedStatus(int id, boolean isBlocked) {
        return userRepository.updateUserBlockedStatus(id, isBlocked);
    }
}
