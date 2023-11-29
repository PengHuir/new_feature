package com.echo.feature.lock;

import java.util.concurrent.TimeUnit;

/**
 * 两个线程交替打印
 *
 * @author ph.zhang
 * Created by on 2023/11/28 18:18
 */
public class AlternatePrintTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.println((char) i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notify();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i <= 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    lock.notify();
                    System.out.println(i);
                }
                lock.notify();
            }
        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.start();
    }
}
