package com.my;

import java.util.TreeSet;

/**
 * Created by alexandr on 10.06.15.
 */
public class Person1 {

    public static void main(String[] args) {
        TreeSet<Person1> people = new TreeSet<Person1>();

        try {
            people.add(new Person1("Alexandr"));
            people.add(new Person1("Tatu"));
            people.add(new Person1("Tom"));
            people.add(new Person1("Tamara"));

            for (Person1 p : people)
                System.out.println(p.name);
        } catch (ClassCastException cce){
            System.out.print("ClassCastException");
        }
    }


    private String name;

    public Person1(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
