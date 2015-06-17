package com.messages.observer;

/**
 ** {@link https://ru.wikipedia.org/wiki/Наблюдатель_(шаблон_проектирования)}
 ** {@link http://itvdn.com/ru/patterns/observer}
 ** {@link http://cpp-reference.ru/patterns/behavioral-patterns/observer/}
 * {@link http://alexprivalov.org/design-patterns-cpp/#builder}
 * {@link http://habrahabr.ru/post/210288/}
 * {@link https://ru.wikipedia.org/wiki/Шаблон_проектирования}
 * *******************************************
 * Наблюдатель (шаблон проектирования)
 */

// В примере описывается получение данных от метеорологической станции (класс WeatherData, рассылатель событий) и
//использование их для вывода на экран (класс CurrentConditionsDisplay, слушатель событий).
//Слушатель регистрируется у наблюдателя с помощью метода registerObserver (при этом слушатель заносится в список observers).
//Регистрация происходит в момент создания объекта currentDisplay, т.к. метод registerObserver применяется в конструкторе.
//При изменении погодных данных вызывается метод notifyObservers, который в свою очередь вызывает метод update
//у всех слушателей, передавая им обновлённые данные.
public class WeatherStation
{
    public static void main(String[] args)
    {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(29, 65, 30.4f);
        weatherData.setMeasurements(39, 70, 29.4f);
        weatherData.setMeasurements(42, 72, 31.4f);
    }
}
