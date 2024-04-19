package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> keyboardInputFlux = Flux.create(sink -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                printWithThread("Type something (or 'exit' to quit): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    sink.complete(); // Flux를 종료
                    scanner.close();
                    break;
                }

                sink.next(input); // 입력된 문자열을 Flux에 방출
            }
        }, FluxSink.OverflowStrategy.IGNORE); // 오버플로우 시 무시
        CountDownLatch latch = new CountDownLatch(1);
        keyboardInputFlux
                .doOnTerminate(latch::countDown)
                .subscribeOn(Schedulers.single())
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .doOnNext(data -> printWithThread("onNext: " + data))
                .flatMap(data -> Flux.just(String.format("Processed<<%s>>", data)))
                .doOnNext(data -> printWithThread("onProcessedNext: " + data))
                .subscribe(
                data -> printWithThread("Received: " + data),
                error -> printWithThread("Error: " + error),
                () -> printWithThread("Complete!")
        );
        Flux.interval(Duration.ofSeconds(3))
                .take(3)
                .subscribe(s -> printWithThread( s + "seconds elapsed"));
        printWithThread("Waiting for terminal signal...");

        latch.await();

    }

    public static void printWithThread(String data){
        System.out.printf("[%s] %s\n",Thread.currentThread().getName(),data);
    }
}