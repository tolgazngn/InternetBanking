package org.internetbanking.dataaccsess.customer;

import org.internetbanking.business.entity.person.Customer;

import java.sql.ResultSet;

public interface ICustomer {
    public boolean addCustomer(Customer customer);
    public ResultSet selectCustomer(String tc);
    public boolean changePassword(String tc, String password);
    public boolean changeEmail(String tc, String email);
    public boolean changePhoneNumber(String tc, String phoneNumber);
}
