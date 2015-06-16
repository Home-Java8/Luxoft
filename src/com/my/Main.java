package com.my;

/**
 * Created by Саша on 09.06.2015.
 */
public class Main {

    private final static String[] aStr1 = {"one", "two", "three", "four", "five"};
    private final static Integer[] aInt2 = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
//        f(null);

        try {
            myPrint(aStr1);
            myPrint(aInt2);
//            myPrint(null);
        } catch (NullPointerException npe){
            System.out.println("NullPointerException");
        }


        SomeClass a = new SomeClass();
        System.out.println( a.someMethod() ); // It works
//        System.out.println( a.someField ); // ошибка
        System.out.println( ( (Interface1) a).someField ); // 100
        System.out.println( Interface1.someField ); // 100

    }


    public static <T> void myPrint(T[] value) throws NullPointerException, IndexOutOfBoundsException {
        for (T v : value) System.out.println(v);
    }


    public static void f(NullPointerException e){
        try {
            throw e;
        } catch (NullPointerException npe){
            f (npe);
        }
    }

}


interface Interface1 {
    int someField = 100;
//    public static int someField = 100;
    String someMethod();
}

interface Interface2 {
    int someField = 200;
//    public static int someField = 200;
    String someMethod();
}

class SomeClass implements Interface1, Interface2 {
    public String someMethod() {
        return "It Works";
    }
}
