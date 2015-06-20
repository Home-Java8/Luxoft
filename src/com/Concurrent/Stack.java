package com.Concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Саша on 20.06.2015.
 * {@link http://www.duct-tape-architect.ru/?p=294}
 * ************************************************
 * 5) Напишите минимальный неблокирующий стек (всего два метода — push() и pop()).
 */
public class Stack {

    private final AtomicReference<Element> headRef = new AtomicReference<Element>(null);

    public <T> void push(T value) {
        Element newHead = new Element();
        newHead.value   = value;
        Element oldHead;
        do {
            oldHead      = headRef.get();
            newHead.next = oldHead;
        } while ( !headRef.compareAndSet(oldHead, newHead) );
    }

    public <T> T pop() {
        Element oldHead = null;
        Element newHead = null;
        do {
            oldHead = headRef.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while ( !headRef.compareAndSet(oldHead, newHead) );
        return (T) oldHead.value;
    }

    public static class Element <T>{
        private T value;
        private Element next;
    }

}
