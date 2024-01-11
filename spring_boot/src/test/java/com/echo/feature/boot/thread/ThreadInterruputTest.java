package com.echo.feature.boot.thread;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/27 16:29
 */
public class ThreadInterruputTest {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1);
        myThread.interrupt();
        Thread.sleep(1);
        System.exit(0);
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
           while (true) {
               boolean interrupted = this.isInterrupted();
               System.out.println("interrupted = " + interrupted);
           }
        }
    }
}
