package com.echo.feature.boot.thread;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    static volatile boolean running = true;

    private static void m () {
        System.out.println("method start");
        while (running) {

        }
        System.out.println("method end");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(VolatileTest::m).start();

        TimeUnit.SECONDS.sleep(1);

        VolatileTest.running = false;
    }
}
