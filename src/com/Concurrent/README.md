
Java Concurrent:
================

* `Обзор java.util.concurrent` [Обзор java.util.concurrent](http://habrahabr.ru/company/luxoft/blog/157273/)
* [https://ru.wikipedia.org/wiki/Неблокирующая_синхронизация](https://ru.wikipedia.org/wiki/Неблокирующая_синхронизация)
* `Пять вещей, которые вы не знали о ... пакете java.util.concurrent. Часть 1` [Пять вещей, которые вы не знали о ... пакете java.util.concurrent. Часть 1](http://www.ibm.com/developerworks/ru/library/j-5things4/)
* `о параллелизме в Java: знакомство с пакетом util.concurrent` [о параллелизме в Java: знакомство с пакетом util.concurrent](http://www.nestor.minsk.by/sr/2007/02/sr70216.html)
* `Golovach Ivan` [Собеседование по Java Concurrency](https://rsdn.ru/forum/java/3622844.1)
* `Собеседование по Java concurrency` [Собеседование по Java concurrency](http://www.duct-tape-architect.ru/?p=294)
* `Многопоточность в Java` [Использование семафоров (java.util.concurrent.Semaphore)](http://movejava.blogspot.com/2013/06/javautilconcurrentsemaphore.html)
* `Разбор Concurrency` [Разбор Concurrency](http://javatalks.ru/topics/3703)
* `Объединение потоков выполнить краткий` [Объединение потоков выполнить краткий](http://javist.ru/category/java-util-concurrent/)
* `Теория и Практика Java: О параллелизме простыми словами (почти)` [Теория и Практика Java: О параллелизме простыми словами (почти)](http://www.ibm.com/developerworks/ru/library/j-jtp1126/)




Параллельные коллекции (http://www.ibm.com/developerworks/ru/library/j-5things4/):
==================================================================================

1 `Concurrent Collections` (вместо базового враппера Collections.synchronizedList с блокированием доступа ко всей коллекции) используются блокировки по сегментам данных или же оптимизируется работа для параллельного чтения данных по [wait-free](https://ru.wikipedia.org/wiki/Неблокирующая_синхронизация) алгоритмам.
                           Отказ от традиционных примитивов блокировки таких как: "семафоры", "мьютексы". Разделение доступа между потоками идёт за счёт "атомарных операций".

> '[CopyOnWrite коллекции](http://)': CopyOnWriteArrayList, CopyOnWriteArraySet

> '[Scalable Maps](http://)': ConcurrentMap, ConcurrentHashMap, ConcurrentNavigableMap, ConcurrentSkipListMap, ConcurrentSkipListSet

2 `Queues` Если потоки, разгребающие очередь перестанут справляться с наплывом данных, то можно довольно быстро схлопотать out of memory или перегрузить IO/Net

> '[Non-Blocking Queues](http://)': ConcurrentLinkedQueue, ConcurrentLinkedDeque

> '[Blocking Queues](http://)': BlockingQueue, ArrayBlockingQueue, DelayQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue, BlockingDeque, LinkedBlockingDeque, TransferQueue, LinkedTransferQueue

3 `Synchronizers` чаще всего используются для ограничения количества потоков при работе с аппаратными ресурсами или файловой системой

>  Semaphore, CountDownLatch, CyclicBarrier, Exchanger, Phaser

4 `Executors` для запуска асинхронных задач с возможностью получения результатов через Future и Callable интерфейсы, а также сервисы и фабрики для создания thread pools

> '[Future and Callable](http://)': Future, RunnableFuture, Callable, FutureTask, Delayed, ScheduledFuture, RunnableScheduledFuture

> '[Executor Services](http://)': Executor, ExecutorService, ScheduledExecutorService, AbstractExecutorService

> '[ThreadPoolExecutor & Factory](http://)': Executors, ThreadPoolExecutor, ScheduledThreadPoolExecutor, ThreadFactory, RejectedExecutionHandler

> '[Fork Join](http://)': ForkJoinPool, ForkJoinTask, RecursiveTask, RecursiveAction, ForkJoinWorkerThread [Fork Join фреймворк для решения рекурсивных задач, работающих по алгоритмам разделяй и влавствуй или Map Reduce](http://)

> '(Completion Service](http://)': CompletionService, ExecutorCompletionService

5 `Locks` Интерфейс, который описывает альтернативные методы стандарным wait/notify (часто используется в многопоточных сервисах и кешах, хороший прирост производительности по сравнению с блоками synchronized)

> Condition, Lock, ReentrantLock, ReadWriteLock, ReentrantReadWriteLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock, LockSupport, AbstractOwnableSynchronizer, AbstractQueuedSynchronizer, AbstractQueuedLongSynchronizer

6 `Atomics` если в классе нужно синхронизировать доступ к одной простой переменной типа int..., можно использовать конструкции с synchronized, а при использовании атомарных операций [set](http://)/[get](http://), подойдет также и [volatile](http://)

> AtomicBoolean, AtomicInteger, AtomicLong, AtomicIntegerArray, AtomicLongArray, AtomicReference, AtomicMarkableReference, AtomicStampedReference, AtomicReferenceArray, AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicReferenceFieldUpdater



* `TimeUnit` ................. включает в себя значения для всех единиц времени
* `CopyOnWriteArrayList` ..... создание копии массива это слишком дорогая операция как по времени так и по затратам ресурсов памяти, когда имеется множество операций чтения "ArrayList"  но мало операций изменения
* `BlockingQueue` ............ изящно решает проблему передачи элементов, собранных одним потоком для обработки в другой поток без явных хлопот о проблемах синхронизаци
* `ConcurrentMap` ............ если два потока вызывают метод в один и тот же момент времени для каждого из них будет выполнена проверка (и каждый из них выполнит метод put) в результате чего значение сохраненное первым потоком, будет потеряно
* `SynchronousQueues` ........ блокирующая очередь в которой каждая операция добавления должна ждать соответствующей операции удаления в другом потоке и наоборот. Синхронная очередь не имеет никакой внутренней емкости, даже емкости в один элемент

