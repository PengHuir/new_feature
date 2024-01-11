package com.echo.feature.boot.lock;

/**
 * 一个线程持续输出数据，在输出到某个值的时候运行另一个线程
 *
 * @author ph.zhang
 * Created by on 2023/11/28 18:12
 */
public class NumStopTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Run Thread 1");
        });

        final int num = 5;
        final int length = 2 * num;

        new Thread(() -> {
            for (int i = 0; i < length; i++) {
                System.out.println("index: " + i);
                if (i == num - 1) {
                    try {
                        t1.start();
                        t1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
