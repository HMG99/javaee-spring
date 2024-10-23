package com.digi.repository.impl;

import com.digi.enums.Status;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.HibernateUtil;
import com.digi.util.TokenGenerator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class UserRepositoryJPA implements UserRepository {

    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where id = ?", User.class);
        nativeQuery.setParameter(1, id);
        return nativeQuery.uniqueResult();
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where email = ?", User.class);
        nativeQuery.setParameter(1, email);
        return nativeQuery.uniqueResult();
    }

    @Override
    public void verification(int id) {
        User user = getUserById(id);
        user.setStatus(Status.ACTIVE);
        user.setVerificationCode(null);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }

    @Override
    public void changePassword(int id, String password) {
        User user = getUserById(id);
        user.setPassword(TokenGenerator.passwordEncoder(password));
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }

    @Override
    public void setResetToken(int id, String token) {
        User user = getUserById(id);
        user.setResetToken(token);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }
}
