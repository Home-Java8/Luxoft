package com.Concurrent.pool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Саша on 21.06.2015.
 * {@link http://javist.ru/category/java-util-concurrent/}
 * ************************************************
 * создать пул потоков
 */
public class UsePool {

    public static void main(String args[]) {
        Random            random = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Sum up wait times to know when to shutdown
        int waitTime = 500;
        for (int i=0; i<10; i++) {
            String     name = "NamePrinter-" + i;
            int        time = random.nextInt(1000);
            waitTime       += time;
            Runnable runner = new NamePrinter(name, time);
            System.out.println("Adding: " + name + " / " + time);
            executor.execute(runner);
        }

        try {
            Thread.sleep(waitTime);
            executor.shutdown();
            executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {}

        System.exit(0);
    }

}