package com.hsryuuu.webflux.sample;

public class SampleMain {
    public static void main(String[] args) throws InterruptedException {
        MyPublisher publisher = new MyPublisher();
        MySubscriber subscriber = new MySubscriber();

        System.out.println("Thread " + Thread.currentThread().getName() + ", ### 구독 ###");
        publisher.subscribe(subscriber);

        // 데이터 발행
        System.out.println("Thread " + Thread.currentThread().getName() + ", ### 데이터 발행 ###");
        publisher.notifySubscribers(new Message(0, "Initial Message"));

        System.out.println("### 0.2초 sleep ###");
        Thread.sleep(200);

        System.out.println("Thread " + Thread.currentThread().getName() + ", check 3");
        publisher.close();

        // 애플리케이션이 종료가 안되는 이유 : subscription executor 가 살아있기 때문!
    }
}
