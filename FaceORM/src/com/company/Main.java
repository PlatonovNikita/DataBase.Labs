package com.company;

import java.sql.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        try {
            VisitorsDB.conect();

            InsertVisitors();//Используется один раз, далее закоментируется (костыль разрешённый Моисеевым).
            //Или используй метотд Clear для очистки

            List<Visitor> visitors = VisitorsDB.getVisitorTable().getVisitorsByPosition("Teacher");
            for (Visitor v: visitors) {
                System.out.println("name:" + v.People.FirstName + "\n   lastName:" + v.People.LastName + "age: " + v.People.Age + " city:" + v.City
                        + " position:" + v.Position);
            }

            Clear();//Очищаем

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void InsertVisitors() throws SQLException {
        VisitorTable visitorTable = VisitorsDB.getVisitorTable();

        visitorTable.insertVisitor(new Visitor(new People("Nikita", "Platonov", 20),
                "Nizhniy", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Olya", "Kazakova", 20),
                "Kanoha", "Genin"));
        visitorTable.insertVisitor(new Visitor(new People("Anton", "Moiseev", 25),
                "Nizhniy Novgorod", "Teacher"));
        visitorTable.insertVisitor(new Visitor(new People("Dimitry", "Tereshin", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Vanya", "Semerenko", 21),
                "ReSHITiha", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Vadim", "Shudrik", 20),
                "Bor", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Sasha", "Sedelnikov", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Egor", "Andronov", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Lena", "Leshehva", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Anton", "Chernov", 36),
                "Nizhniy Novgorod", "Teacher"));
        visitorTable.insertVisitor(new Visitor(new People("Andrey", "Bagaev", 32),
                "Nizhniy Novgorod", "Teacher"));
        visitorTable.insertVisitor(new Visitor(new People("Ulya", "Butusova", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Irina", "Kolchik", 1000),
                "Irak", ""));
        visitorTable.insertVisitor(new Visitor(new People("Ilya", "Gorshkov", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Kolya", "Fedunin", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Ulya", "Chubarova", 32),
                "Nizhniy Novgorod", "Teacher"));
        visitorTable.insertVisitor(new Visitor(new People("Dimitry", "Okunev", 20),
                "Nizhniy Novgorod", "Student"));
        visitorTable.insertVisitor(new Visitor(new People("Kirill", "Polyakov", 20),
                "Nizhniy Novgorod", "Student"));
    }

    static void Clear() throws SQLException {
        for (Visitor v : VisitorsDB.getVisitorTable().getVisitors()){
            VisitorsDB.getVisitorTable().deleteVisitor(v.Id);
        }
        for (People p: VisitorsDB.getPeopleTable().getPeoples()) {
            VisitorsDB.getPeopleTable().deletePeople(p.Id);
        }
    }
}
