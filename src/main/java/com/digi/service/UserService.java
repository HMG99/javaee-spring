package com.digi.service;

import com.digi.email.EmailSender;
import com.digi.enums.Status;
import com.digi.exceptions.UserAlreadyExistsException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.exceptions.ValidationException;
import com.digi.helper.UserHelper;
import com.digi.model.User;
import com.digi.repository.impl.AddressRepositoryJPA;
import com.digi.repository.impl.UserRepositoryJDBC;
import com.digi.repository.impl.UserRepositoryJPA;
import com.digi.util.TokenGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserService {

    public static void saveUser(String name, String surname, String year, String email, String password) {
        String vCode = TokenGenerator.generateVerifyCode();
        try {
            int newYear = Integer.parseInt(year);
            User user = new User(0, name, surname, newYear, email, password, Status.INACTIVE, vCode, null);
            UserHelper.UserValidation(user);
            UserHelper.duplicateCheck(email);
            user.setPassword(TokenGenerator.passwordEncoder(password));
            getUserRepositoryBean().saveUser(user);
            EmailSender.sendEmail(email, "Verification Code", "Your verification code is " + vCode);
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                throw new ValidationException("Year: Incorrect number format");
            }
            if (e instanceof ValidationException) {
                throw e;
            }
            if (e instanceof UserAlreadyExistsException) {
                throw e;
            }
        }

    }

    public static void verification(String email, String verificationCode) {
        User user = getUserRepositoryBean().getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with given email does not exist");
        }
        if (verificationCode != null && !user.getVerificationCode().equals(verificationCode)) {
            throw new ValidationException("Wrong verification code");
        } else if (verificationCode == null) {
            throw new ValidationException("You are verified.");
        }
        getUserRepositoryBean().verification(user.getId());
    }

    public static void setResetToken(String email) {
        User user = getUserRepositoryBean().getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with given email does not exist");
        }
        String token = TokenGenerator.generateResetToken();
        getUserRepositoryBean().setResetToken(user.getId(), token);
        EmailSender.sendEmail(email, "Reset Token", "Reset Token for password change: " + token);
    }

    public static void forgotPassword(String email, String token, String password, String confirmPassword) {
        if (password != null && !password.equals(confirmPassword)) {
            throw new ValidationException("passwords do not match");
        } else if (password == null) {
            throw new ValidationException("password cannot be null");
        }
        if (!UserHelper.isValidPassword(password)) {
            throw new ValidationException("Password must contain at least 8 characters, 1 digit and 1 uppercase");
        }
        User user = getUserRepositoryBean().getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with given email does not exist");
        }
        if (!user.getResetToken().equals(token)) {
            throw new ValidationException("Wrong reset token");
        }

        getUserRepositoryBean().changePassword(user.getId(), TokenGenerator.passwordEncoder(password));
    }


    public static User login(String email, String password) {
        User user = getUserRepositoryBean().getUserByEmail(email);
        if (user == null) {
            throw new ValidationException("Wrong email or password");
        }
        if (!user.getPassword().equals(TokenGenerator.passwordEncoder(password))) {
            throw new ValidationException("Wrong email or password");
        }
        user.setPassword(null);
        return user;
    }

    private static UserRepositoryJPA getUserRepositoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        return applicationContext.getBean("userRepository", UserRepositoryJPA.class);
    }

}
