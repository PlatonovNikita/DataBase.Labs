package com.company;

import java.sql.*;

import java.sql.Connection;

public class VisitorsDB {
    static Connection connection;
    public static final String DB_Driver = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:/c:/JavaPrj/FaceORM/db/FakeDB";
    static VisitorTable visitorTable = null;
    static PeopleTable peopleTable = null;

    public static void conect(){
        try{
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection((DB_URL));

            create(); //Создаёт таблицы в бд. Используется один раз, далее закоментируется
        }
        catch (ClassNotFoundException e){
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
        catch (SQLException e){
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println(e.getMessage());
        }
    }

    public static VisitorTable getVisitorTable() throws SQLException {
        if (visitorTable == null){
            visitorTable = new VisitorTable(connection);
        }
        return visitorTable;
    }

    public static PeopleTable getPeopleTable() throws SQLException {
        if (peopleTable == null){
            peopleTable = new PeopleTable(connection);
        }
        return peopleTable;
    }

    static void create(){
        try {
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE peoples(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "firstName VARCHAR(255) NOT NULL," +
                    "age int NOT NULL);");
            st.execute("CREATE TABLE visitors(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "peopleId BIGINT," +
                    "city VARCHAR(255) NOT NULL," +
                    "position VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY (peopleId) REFERENCES peoples(id)" +
                    ");");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
