package com.digi.repository;

import com.digi.model.Address;

public interface AddressRepository {
    void saveAddress(Address address);
    Address getAddressByUserId(int id);
}
