package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeopleTable {
    Connection connection;
    Statement statement;

    public  PeopleTable(Connection _connection) throws SQLException {
        connection = _connection;
        if(connection != null){
            statement = connection.createStatement();
        }
        else {
            throw new SQLException("Connection not found");
        }
    }

    public int insertPeople(People people) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id from peoples");
        int id = 0;
        while (rs.next()){
            int i = rs.getInt("id");
            if(i > id){
                id = i;
            }
        }
        id += 1;
        statement.execute("INSERT into peoples values (" + id + ", '" + people.LastName + "', '"
                + people.FirstName + "', '" + people.Age + "');");
        return id;
    }

    public void deletePeople(int id) throws SQLException{
        statement.execute("DELETE from peoples WHERE id = '" + id + "'");
    }

    public People getPeople(int id) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE id = '" + id + "'");
        rs.next();
        People result = new People(
                rs.getInt("Id"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getInt("Age")
        );
        return result;
    }

    public List<People> getPeoplesByFirstName(String name) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE firstName = '" + name + "'");
        List<People> result = new ArrayList<People>();
        while (rs.next()){
            result.add(
                    new People(
                            rs.getInt("Id"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getInt("Age")
                    )
            );
        }
        return result;
    }

    public List<People> getPeoplesByLastName(String Name) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE lastName = '" + Name + "'");
        List<People> result = new ArrayList<People>();
        while (rs.next()){
            result.add(
                    new People(
                            rs.getInt("Id"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getInt("Age")
                    )
            );
        }
        return result;
    }

    public List<People> getPeoplesByAge(int age) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE age = '" + age + "'");
        List<People> result = new ArrayList<People>();
        while (rs.next()){
            result.add(
                    new People(
                            rs.getInt("Id"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getInt("Age")
                    )
            );
        }
        return result;
    }

    public List<People> getPeoples() throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from peoples");
        List<People> result = new ArrayList<People>();
        while (rs.next()){
            result.add(
                    new People(
                            rs.getInt("Id"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getInt("Age")
                    )
            );
        }
        return result;
    }
}
