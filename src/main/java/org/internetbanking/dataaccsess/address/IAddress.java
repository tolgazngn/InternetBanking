package org.internetbanking.dataaccsess.address;

import org.internetbanking.business.entity.person.CustomerAddress;

import java.sql.ResultSet;

public interface IAddress {
    public boolean addAddress(CustomerAddress address);
    public boolean deleteAddress(String tc);
    public ResultSet select(String tc);
}
