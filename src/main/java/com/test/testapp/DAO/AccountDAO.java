package com.test.testapp.DAO;

import com.test.testapp.entity.Account;

import java.util.List;

public interface AccountDAO {

    boolean add(Account account);

    List<Account> getAll();

    Account getByName(String name);

    int updateSurname(String name, String surname);

    int remove(String name);
}
