package com.messages;

import java.util.*;

/**
 * Created by alexandr on 17.06.15.
 * ********************************
 *
 * {@link http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java}
 ** {@link http://www.mkyong.com/java/how-to-sort-a-map-in-java/}
 * {@link http://www.tutorialspoint.com/java/util/collections_sort_comparable.htm}
 * {@link http://stackoverflow.com/questions/6957631/sort-java-collection}
 * {@link http://stackoverflow.com/questions/2477261/how-to-sort-a-collectiont}
 * {@link http://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java-specific-situation}
 * {@link http://tutorials.jenkov.com/java-collections/sorting.html}
 *
 * {@link http://habrahabr.ru/post/128827/}
 * {@link http://www.javenue.info/post/76}
 * {@link http://stackoverflow.com/questions/12473550/how-to-convert-string-date-to-long-millseconds}
 *
 ** {@link http://www.golovachcourses.com/threads-advanced/}
 * {@link http://habrahabr.ru/company/luxoft/blog/157273/}
 * {@link http://javist.ru/category/java-util-concurrent/}
 * {@link http://www.javaprobooks.ru/java-программирование/использование-java-util-concurrent-cyclicbarrier.html}
 */
public class Main {

    public static void main(String[] args) {

        Message[] message = new Message[100];

        message[0] = new Message("Aaaaaaa", new Date(), 3);
        message[1] = new Message("Bbbbbbb", new Date(), 3);
        message[2] = new Message("Ccccccc", new Date(), 3);
        message[3] = new Message("Ddddddd", new Date(), 2);
        message[4] = new Message("Eeeeeee", new Date(), 2);
        message[5] = new Message("Iiiiiii", new Date(), 1);
        message[5] = new Message("Kkkkkkk", new Date());


////        Queue queue = new Queue(20, 5000);
//
//        try {
////            long d1 = new Date().getTime();
////            Thread.sleep(3000);
////            long d2 = new Date().getTime();
////            System.out.println(d1);
////            System.out.println(d2);
////            System.out.println(d2-d1);
//        }catch (Exception e){}


        Observers observers = new Observers();
        observers.add(new EmptyObserver() {
            public void objectCreated(Object obj) { /* реализация */ }
        });


//        List<String> aList = new ArrayList<>();
        List<String> aList = new LinkedList<>();
        aList.add("55555");
        aList.add("333");
        aList.add("22");
        aList.add("4444");
        aList.add("1");

//        Collections.sort(aList, new Comparator<String>() {
//            @Override
//            public int compare(String str1, String str2) {
////                if(Integer.valueOf(str1) == Integer.valueOf(str1))
////                    return 0;
////                else if(Integer.valueOf(str1) < Integer.valueOf(str1))
////                    return 1;
////                else
////                    return -1;
//
//                if(str1.length() == str1.length())
//                    return 0;
//                else if(str1.length() > str1.length())
//                    return -1;
//                else
//                    return 1;
//            }
//        });

        Collections.sort(aList);

        for (String a : aList) System.out.println(a);



        System.out.println();

        HashMap<String,Double> map = new HashMap<String,Double>();
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>();

        map.put("A",99.5);
        map.put("B",67.4);
        map.put("C",67.4);
        map.put("D",67.3);

        for (Map.Entry e : map.entrySet()) System.out.println(e);
    }

}








interface Observer {
    void objectCreated(Object obj);
    void objectModified(Object obj);
}

class EmptyObserver implements Observer {
    public void objectCreated(Object obj) { }
    public void objectModified(Object obj) { }
}

class Observers<T extends Observer> extends ArrayList<T> {
    public void notifyObjectCreated(Object obj) {
        for (Iterator<T> iter = (Iterator<T>) iterator(); iter.hasNext();)
            iter.next().objectCreated(obj);
    }
    public void notifyObjectModified(Object obj) {
        for (Iterator<T> iter = (Iterator<T>) iterator(); iter.hasNext();)
            iter.next().objectModified(obj);
    }
}


class Subject {
    Observers observers = new Observers();

    private Object field;

    public void setField(Object o) {
        field = o;
        observers.notifyObjectModified(this);
    }
}