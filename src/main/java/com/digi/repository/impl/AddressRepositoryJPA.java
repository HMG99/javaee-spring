package com.digi.repository.impl;

import com.digi.model.Address;
import com.digi.repository.AddressRepository;
import com.digi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class AddressRepositoryJPA implements AddressRepository {

    public static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void saveAddress(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(address);
        transaction.commit();
    }

    @Override
    public Address getAddressByUserId(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery<Address> nativeQuery = session.createNativeQuery("select * from address where user_id = ?", Address.class);
        nativeQuery.setParameter(1, id);
        return nativeQuery.uniqueResult();
    }


}
