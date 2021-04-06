package com.spd.baraholka.user.persistance;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.Owner;
import com.spd.baraholka.user.persistance.entities.User;

import java.util.Optional;
import java.util.Set;
import java.util.List;

public interface PersistenceUserService {

    User create(User user);

    User selectUserById(int id);

    Optional<Boolean> existsByEmail(String email);

    Optional<Integer> count();

    List<User> selectAllUsers();

    Optional<Boolean> isExist(int value);

    User updateUserMainInfo(User user);

    Optional<Owner> selectOwner(int id);

    int updateAvatar(int userId, String imageUrl);

    Optional<User> findByEmail(String email);

    Set<Role> getRolesByUserId(int id);

    User selectUserMainInfo(int userId);
}
