package org.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class ParallelSequential {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(s -> System.out.println("[" + Thread.currentThread().getName() + "] parallel " + s))
                .sequential()
                .doOnNext(s -> System.out.println("[" + Thread.currentThread().getName() + "] after sequential " + s))
                .doOnTerminate(latch::countDown)
                .subscribe(s -> System.out.println("[" + Thread.currentThread().getName() + "] on complete " + s));
        latch.await();
    }
}
