package com.digi.repository;

import com.digi.model.User;

public interface UserRepository {
    void saveUser(User user);
    User getUserById(int id);
    User getUserByEmail(String email);
    void verification(int id);
    void changePassword(int id, String password);
    void setResetToken(int id, String token);
}
