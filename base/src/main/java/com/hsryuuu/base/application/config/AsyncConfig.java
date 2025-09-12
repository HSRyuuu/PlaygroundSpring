package com.hsryuuu.base.application.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor customThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 기본 쓰레드 수
        executor.setMaxPoolSize(20); // 최대 쓰레드 수
        executor.setQueueCapacity(100); // 대기 큐 크기
        executor.setThreadNamePrefix("AsyncThreadPool-"); // 쓰레드 이름 접두사
        executor.initialize(); // 초기화
        return executor;
    }
}
