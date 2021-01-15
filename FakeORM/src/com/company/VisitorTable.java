package com.company;

import java.sql.*;
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
//        statement.execute("INSERT into visitors (peopleId, city, position) values ('" + visitor.People.Id + "', '"
//                + visitor.City + "', '" + visitor.Position + "');");
        var stmt = connection.prepareStatement("INSERT into visitors (peopleId, city, position) values (?,?,?);");
        stmt.setInt(1, visitor.People.Id);
        stmt.setString(2, visitor.City);
        stmt.setString(3, visitor.Position);
        stmt.execute();
    }

    public void deleteVisitor(int id) throws SQLException{
/*        statement.execute("DELETE from visitors WHERE id = '" + id + "'");*/
        var stmt = connection.prepareStatement("DELETE from visitors WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public Visitor getVisitor(int id) throws  SQLException{
//        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE id = '" + id + "'");
        var stmt = connection.prepareStatement("SELECT * from visitors WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE _name = '" + name + "'");
        var stmt = connection.prepareStatement("SELECT * from visitors WHERE _name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE city = '" + city + "'");
        var stmt = connection.prepareStatement("SELECT * from visitors WHERE city = ?");
        stmt.setString(1, city);
        ResultSet rs = stmt.executeQuery();
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
//        ResultSet rs = statement.executeQuery("SELECT * from visitors WHERE position = '" + position + "'");
        var stmt = connection.prepareStatement("SELECT * from visitors WHERE position = ?");
        stmt.setString(1, position);
        ResultSet rs = stmt.executeQuery();
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
