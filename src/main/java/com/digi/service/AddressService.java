package com.digi.service;

import com.digi.model.Address;
import com.digi.repository.impl.AddressRepositoryJPA;
import com.digi.repository.impl.UserRepositoryJPA;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressService {
    public static void saveAddress(Address address) {
        getAddressRepositoryBean().saveAddress(address);
    }

    public static Address getAddressByUserId(int id) {
        return getAddressRepositoryBean().getAddressByUserId(id);
    }

    private static AddressRepositoryJPA getAddressRepositoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        return applicationContext.getBean("addressRepository", AddressRepositoryJPA.class);
    }

}
