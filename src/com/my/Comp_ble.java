package com.my;

import java.util.TreeSet;

/**
 * Created by alexandr on 10.06.15.
 * {@link http://echuprina.blogspot.com/2012/02/comparable-comparator.html}
 * ************************************************************************
 * Сортировка и упорядочивание. Интерфейсы Comparable и Comparator
 */
public class Comp_ble implements Comparable {
    String str;
    int number;


    public static void main(String[] args) {
        TreeSet<Comp_ble> ex = new TreeSet<Comp_ble>();
        ex.add(new Comp_ble("Stive Global", 121));
        ex.add(new Comp_ble("Stive Global", 221));
        ex.add(new Comp_ble("Nancy Summer", 3213));
        ex.add(new Comp_ble("Aaron Eagle", 3123));
        ex.add(new Comp_ble("Barbara Smith", 88786));

        for(Comp_ble e : ex) {
            System.out.println("Str: " + e.str + ", number: " + e.number);
        }
    }


    Comp_ble(String str, int number) {
        this.str = str;
        this.number = number;
    }

//    @Override
//    public int compareTo(Object obj) {
//        Comp_ble entry = (Comp_ble) obj;
//
//        int result = str.compareTo(entry.str);
//        if(result != 0) {
//            return result;
//        }
//
//        result = number - entry.number;
//        if(result != 0) {
//            return (int) result / Math.abs( result );
//        }
//        return 0;
//    }

//    @Override
//    public int compareTo(Object obj) {
//        Comp_ble entry = (Comp_ble) obj;
//
//        int result = entry.str.compareTo(str); // значения меняются местами
//        if(result != 0) {
//            return result;
//        }
//
//        result = entry.number - number; // значения меняются местами
//        if(result != 0) {
//            return (int) result / Math.abs( result );
//        }
//        return 0;
//    }

    @Override
    public int compareTo(Object obj) {
        Comp_ble entry = (Comp_ble) obj;

        // сначала ищем позицию пробела с помощью функции indexOf(), после начиная с найденной позиции выкусываем подстроку
        String str1 = str.substring(str.indexOf(" "));
        String str2 = entry.str.substring(entry.str.indexOf(" "));

        // после получения подстрокой производим сравнение
        int result = str1.compareTo(str2);
        if(result != 0)
            return result;

        result = number - entry.number;
        if(result != 0)
            return (int) result / Math.abs( result );

        return 0;
    }
}
