package org.internetbanking.dataaccsess.admin;

import org.internetbanking.business.entity.person.Admin;

import java.sql.ResultSet;

public interface IAdmin {
    public ResultSet selectAdmin(String username);

    public void setAdmin(String username, Admin admin);
}
