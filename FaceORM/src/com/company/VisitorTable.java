package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class VisitorTable {
    Connection connection;
    Statement statement;
    PeopleTable peoples;

    public  VisitorTable(Connection _connection) throws SQLException {
        connection = _connection;
        if(connection != null){
            statement = connection.createStatement();
            peoples = VisitorsDB.getPeopleTable();
        }
        else {
            throw new SQLException("Connection not found");
        }
    }

    public void insertVisitor(Visitor visitor) throws SQLException {
        if(visitor.People.Id == 0){
            visitor.People.Id = peoples.insertPeople(visitor.People);
        }
        statement.execute("INSERT into visitors (peopleId, city, position) values ('" + visitor.People.Id + "', '"
                + visitor.City + "', '" + visitor.Position + "');");
    }

    public void deleteVisitor(int id) throws SQLException{
        statement.execute("DELETE from visitors WHERE id = '" + id + "'");
    }

    public Visitor getVisitor(int id) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE id = '" + id + "'");
        rs.next();
        Visitor result = new Visitor(
                rs.getInt("Id"),
                peoples.getPeople(rs.getInt("peopleId")),
                rs.getString("City"),
                rs.getString("Position")
        );
        return result;
    }

    public List<Visitor> getVisitorsByName(String name) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE _name = '" + name + "'");
        List<Visitor> result = new ArrayList<Visitor>();
        while (rs.next()){
            result.add(
                    new Visitor(
                            rs.getInt("Id"),
                            peoples.getPeople(rs.getInt("peopleId")),
                            rs.getString("City"),
                            rs.getString("Position")
                    )
            );
        }
        return result;
    }

    public List<Visitor> getVisitorsByCity(String city) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE city = '" + city + "'");
        List<Visitor> result = new ArrayList<Visitor>();
        while (rs.next()){
            result.add(
                    new Visitor(
                            rs.getInt("Id"),
                            peoples.getPeople(rs.getInt("peopleId")),
                            rs.getString("City"),
                            rs.getString("Position")
                    )
            );
        }
        return result;
    }

    public List<Visitor> getVisitorsByPosition(String position) throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE position = '" + position + "'");
        List<Visitor> result = new ArrayList<Visitor>();
        while (rs.next()){
            result.add(
                    new Visitor(
                            rs.getInt("Id"),
                            peoples.getPeople(rs.getInt("peopleId")),
                            rs.getString("City"),
                            rs.getString("Position")
                    )
            );
        }
        return result;
    }

    public List<Visitor> getVisitors() throws  SQLException{
        ResultSet rs = statement.executeQuery("SELECT * from visitors");
        List<Visitor> result = new ArrayList<Visitor>();
        while (rs.next()){
            result.add(
                    new Visitor(
                            rs.getInt("Id"),
                            peoples.getPeople(rs.getInt("peopleId")),
                            rs.getString("City"),
                            rs.getString("Position")
                    )
            );
        }
        return result;
    }

    public boolean Empty() throws SQLException {
        return !statement.executeQuery("SELECT * from visitors").next();
    }
}
