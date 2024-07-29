package org.springDemo.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncService {

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    Executor executor;

    @Async("threadPoolTaskExecutor")
    public void asyncPrint() {
        try{
            for (int i = 0; i < 20; i++) {
                TimeUnit.SECONDS.sleep(2);
                log.info("sleep");
            }
        }catch (InterruptedException e){
            log.error(e.getMessage());
        }

    }

    public Integer asyncAdd(Integer num1, Integer num2,Integer num3) {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                return num1+10;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                return num2+10;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                return num3+10;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(f1, f2, f3);
        allFutures.join();
        return f1.join()+f2.join()+f3.join();
    }
}
