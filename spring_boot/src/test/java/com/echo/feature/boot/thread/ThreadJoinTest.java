package com.echo.feature.boot.thread;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/27 17:27
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("index: " + i);
            }
            System.out.println("Thread 1 end");
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 end");
        });

        t1.start();
        t2.start();
    }
}
