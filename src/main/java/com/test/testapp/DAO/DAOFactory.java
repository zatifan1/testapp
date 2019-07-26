package com.test.testapp.DAO;

public class DAOFactory implements IDAOFactory {

    private static IDAOFactory factory;

    private DAOFactory(){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loading success!");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance(){
        if (factory == null){
            factory = new DAOFactory();
        }
        return factory;
    }

    public AccountDAO getAccountDAO() {
        return new AccountJDBCDAO();
    }
}
