package com.Concurrent;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Саша on 21.06.2015.
 * {@link http://www.duct-tape-architect.ru/?p=294}
 * ************************************************
 * 46) Напишите реализацию класса с неблокирующим методом BigInteger next(), который возвращает элементы последовательности: [1, 2, 4, 8, 16, ...]. Код должен корректно работать в многопоточной среде.
 */
public class Counter {

    private AtomicReference refCounter = new AtomicReference(null);

    public BigInteger next() {
        BigInteger oldValue;
        BigInteger newValue;
        do {
            oldValue = (BigInteger) refCounter.get();
            newValue = oldValue == null ? BigInteger.valueOf(1) : oldValue.shiftLeft(1);
        } while( !refCounter.compareAndSet(oldValue, newValue) );
        return newValue;
    }

}
