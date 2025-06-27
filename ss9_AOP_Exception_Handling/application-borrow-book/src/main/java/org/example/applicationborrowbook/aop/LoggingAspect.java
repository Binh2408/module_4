package org.example.applicationborrowbook.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.applicationborrowbook.model.Book;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Before("execution(* org.example.applicationborrowbook.controller.BookController.*(..))")
    public void countVisited(JoinPoint joinPoint) {
        int count = atomicInteger.incrementAndGet();
        System.out.println("Đã có " + count + " lượt truy cập. Truy ở chức năng: " + joinPoint.getSignature().getName());
    }

    @After("execution(* org.example.applicationborrowbook.service.IBookService.save(..))")
    public void logBookChange(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        Book book = (Book) objects[0];
        System.out.println("Đã cập nhật lại số lượng sách: " + book.getName() + " là: " + book.getQuantity());
    }
}
