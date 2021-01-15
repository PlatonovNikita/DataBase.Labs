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
//        statement.execute("INSERT into peoples values (" + id + ", '" + people.LastName + "', '"
//                + people.FirstName + "', '" + people.Age + "');");
        var stmt = connection.prepareStatement("INSERT into peoples values (?,?,?,?)");
        stmt.setInt(1, id);
        stmt.setString(2, people.LastName);
        stmt.setString(3, people.FirstName);
        stmt.setInt(4, people.Age);
        stmt.execute();
        return id;
    }

    public void deletePeople(int id) throws SQLException{
//        statement.execute("DELETE from peoples WHERE id = '" + id + "'");
        var stmt = connection.prepareStatement("DELETE from peoples WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public People getPeople(int id) throws  SQLException{
//        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE id = '" + id + "'");
        var stmt = connection.prepareStatement("SELECT * from peoples WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE firstName = '" + name + "'");
        var stmt = connection.prepareStatement("SELECT * from peoples WHERE firstName = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE lastName = '" + Name + "'");
        var stmt = connection.prepareStatement("SELECT * from peoples WHERE lastName = ?");
        stmt.setString(1, Name);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from peoples WHERE age = '" + age + "'");
        var stmt = connection.prepareStatement("SELECT * from peoples WHERE age = ?");
        stmt.setInt(1, age);
        ResultSet rs = stmt.executeQuery();
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
