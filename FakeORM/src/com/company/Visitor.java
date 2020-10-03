package com.company;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public class Visitor {
    public int Id;
    public People People;
    public String City;
    public String Position;

    public Visitor(int id, People people, String city, String position){
        People = people; City = city; Position = position; Id = id;
    }

    public Visitor(People people, String city, String position){
        People = people; City = city; Position = position; Id = 0;
    }
}
