package com.my;

import java.util.TreeSet;

/**
 * Created by alexandr on 10.06.15.
 * {@link http://metanit.com/java/tutorial/5.6.php}
 * ************************************************
 * Сортировка: интерфейсы Comparable и Comporator
 */

// Для того, чтобы объекты Person можно было сравнить и сортировать, они должны применять интерфейс Comparable<E>
// Интерфейс Comparable содержит один единственный метод int compareTo(E item), который сравнивает текущий объект с объектом, переданным в качестве параметра.
public class Person2 implements Comparable<Person2> {
//public class Person2 {

    public static void main(String[] args) {
        TreeSet<Person2> people = new TreeSet<Person2>();
//        List<Person2> people = new ArrayList<Person2>();
//        List<Person2> people = new LinkedList<Person2>();

        people.add(new Person2("Alexandr"));
        people.add(new Person2("Tatu"));
        people.add(new Person2("Tom"));
        people.add(new Person2("Tamara"));

        for (Person2 p : people)
            System.out.println(p.name);
    }


    private String name;

    public Person2(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    // Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который передается через параметр.
    // Если метод вернет положительное число, то, наоборот, после второго объекта.
    // Если метод возвратит ноль, значит, оба объекта равны.
    @Override
    public int compareTo(Person2 p) {
//        return name.compareTo(p.getName());
        return name.length() - p.getName().length();
    }
}
