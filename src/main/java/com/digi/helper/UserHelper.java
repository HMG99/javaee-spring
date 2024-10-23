package com.digi.helper;

import com.digi.exceptions.UserAlreadyExistsException;
import com.digi.exceptions.ValidationException;
import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryJDBC;

public class UserHelper {
    public static final String USER_NAME_REGEX = "[A-Z][a-z]+";
    public static final String USER_SURNAME_REGEX = "[A-Z][a-z]+";
    public static final String USER_EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static void UserValidation(User user) {
        if (user.getName() != null && !user.getName().matches(USER_NAME_REGEX)) {
            throw new ValidationException("Name format is incorrect");
        } else if (user.getName() == null) {
            throw new ValidationException("Name cannot be null");
        }
        if (user.getSurname() != null && !user.getSurname().matches(USER_SURNAME_REGEX)) {
            throw new ValidationException("Surname format is incorrect");
        } else if (user.getSurname() == null) {
            throw new ValidationException("Surname cannot be null");
        }
        if (!isValidYear(user.getYear())) {
            throw new ValidationException("Year must be between 1945 and 2010");
        }
        if (user.getEmail() != null && !user.getEmail().matches(USER_EMAIL_REGEX)) {
            throw new ValidationException("Email format is incorrect");
        } else if (user.getEmail() == null) {
            throw new ValidationException("email cannot be null");
        }
        if (user.getPassword() != null && !isValidPassword(user.getPassword())) {
            throw new ValidationException("Password must contain at least 8 characters, 1 digit and 1 uppercase");
        } else if (user.getPassword() == null) {
            throw new ValidationException("Password cannot be null");
        }
    }

    private static boolean isValidYear(int year) {
        return year >= 1945 && year <= 2010;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        int digitCount = 0;
        int upperCount = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (Character.isUpperCase(c)) {
                upperCount++;
            }
        }
        return digitCount > 0 && upperCount > 0;
    }

    public static void duplicateCheck(String email) {
        UserRepositoryJDBC userRepositoryJDBC = new UserRepositoryJDBC();
        User user = userRepositoryJDBC.getUserByEmail(email);
        if(user != null) {
            throw new UserAlreadyExistsException("User with given email already exists.");
        }

    }

}
