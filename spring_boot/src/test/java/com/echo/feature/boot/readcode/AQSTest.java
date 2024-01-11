package com.echo.feature.boot.readcode;

import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

    public static void main(String[] args) {
        int i = 0;
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        try {
            i++;
        } finally {
            lock.unlock();
        }
    }
}
