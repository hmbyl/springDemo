package org.springDemo.common;

public class MyRunnable implements Runnable{

    private String threadName;

    public MyRunnable(String threadName){
        this.threadName=threadName;
    }

    @Override
    public void run() {
        System.out.printf("Thread Name:%s\n",threadName);
    }
}
