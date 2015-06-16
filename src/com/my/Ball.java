package com.my;

/**
 * Created by Саша on 09.06.2015.
 */
public abstract class Ball {

}


class Value<T> {
    public static<T> T defaultValue() {
        return null;
    }

    public T getOrDefault(T value, T defaultValue) {
        return ( value != null ) ? value : defaultValue;
    }
}
