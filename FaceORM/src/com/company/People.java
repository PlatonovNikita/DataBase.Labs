package com.company;

public class People {
    public int Id;
    public String LastName;
    public String FirstName;
    public int Age;

    public People(int id, String firstName, String lastName, int age){
        Id = id; FirstName = firstName; LastName = lastName; Age = age;
    }

    public People(String firstName, String lastName, int age){
        Id = 0; FirstName = firstName; LastName = lastName; Age = age;
    }
}
