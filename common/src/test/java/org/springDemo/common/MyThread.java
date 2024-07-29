package org.springDemo.common;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class MyThread extends Thread{

    private String threadName;

    private  String myName;
    public AtomicInteger inc = new AtomicInteger();


    public MyThread(String threadName){
        this.threadName=threadName;
    }

    @Override
    public void run() {
        while(true){
            System.out.printf("Thread Name:%s name:%s inc:%d\n", threadName,myName,inc.getAndIncrement());
            try {
                TimeUnit.SECONDS.sleep(2);
                this.myName="1222";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
