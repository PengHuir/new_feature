package com.echo.feature.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个制定大小的队列，多个线程进行读写操作
 *
 * @author ph.zhang
 * Created by on 2023/11/28 18:24
 */
public class QueueTest {

    static List list = new LinkedList();
    static int MAX = 10;
    static ReentrantLock lock = new ReentrantLock();
    static Condition consumer = lock.newCondition();
    static Condition producer = lock.newCondition();

    static void push(Object o) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(o);
            consumer.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    static Object pull() {
        Object o = null;
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            o = list.get(0);
            list.remove(0);
            producer.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return o;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    push(j);
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    System.out.println(pull());
                }
            }).start();
        }
    }
}
