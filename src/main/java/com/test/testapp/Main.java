package com.test.testapp;

import com.test.testapp.DAO.AccountDAO;
import com.test.testapp.DAO.DAOFactory;
import com.test.testapp.DAO.IDAOFactory;



public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        AccountDAO accountDAO = factory.getAccountDAO();

        System.out.println(accountDAO.getByName("Ivan"));
    }
}
