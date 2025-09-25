package com.hsryuuu.base.sample.transactional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.transaction.support.TransactionSynchronizationManager;

//@Aspect
//@Component
public class TransactionLoggerAspect {

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object logTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        // 트랜잭션 시작 로그
        System.out.printf("▶️ [TX-START] transactionName=%s | active=%s, readOnly=%s\n",
                TransactionSynchronizationManager.getCurrentTransactionName().replace("com.example.playground.spring.transactional.", ""),
                TransactionSynchronizationManager.isActualTransactionActive(),
                TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        );

        try {
            Object result = joinPoint.proceed();

            // 트랜잭션 정상 종료 로그
            System.out.printf("✅ [TX-END] transactionName=%s | active=%s, readOnly=%s\n",
                    TransactionSynchronizationManager.getCurrentTransactionName().replace("com.example.playground.spring.transactional.", ""),
                    TransactionSynchronizationManager.isActualTransactionActive(),
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly()
            );
            return result;
        } catch (Throwable e) {
            // 트랜잭션 예외 로그
            System.out.printf("❌ [TX-EXCEPTION] transactionName=%s | message=%s\n",
                    TransactionSynchronizationManager.getCurrentTransactionName().replace("com.example.playground.spring.transactional.", ""),
                    e.getMessage());
            throw e;
        }
    }
}