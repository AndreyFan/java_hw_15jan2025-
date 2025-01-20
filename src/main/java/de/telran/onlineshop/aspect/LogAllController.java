package de.telran.onlineshop.aspect;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;

//@Aspect
//@Component
//@Slf4j
public class LogAllController {
    // hw-10jan2025:
    //  Добавьте вывод логов (пока в консоль) для всех методов контроллеров, в которых для каждого метода будет
//выводиться следующая информация:
//
//время старта метода, имя метода, значения параметров, которые передаются в метод
//
//время окончания работы метода, значение возвращаемого результата и время работы метода
//Попробуйте реализовать эту функциональность с использованием Spring AOP.
// ( проверил на нескольких методах . Наглядно видно на /users/find/{id}
//    @Around("execution(* de.telran.onlineshop.controller..*(..))")
//    public Object makerLogAllController(@NotNull final ProceedingJoinPoint pjp) throws Throwable {
//        Long startTimeMls = System.currentTimeMillis();
//        Timestamp startTime = new Timestamp(startTimeMls);
//        String argsAsString = Arrays.toString(pjp.getArgs());
//        log.info("!!! befor !!! " + " время старта метода: " + startTime + pjp.getSignature().getName() + "  args={ " + argsAsString + " };");
//
//        Object result = pjp.proceed(); //запуск перехваченного метода
//
//        Long finishTimeMls = System.currentTimeMillis();
//        Timestamp finishTime = new Timestamp(finishTimeMls);
//        String resAsString = result.toString();
//        log.info("!!! after !!! " + " время окончания работы метода: " + finishTime + " Результат: " + resAsString + " отработал за:  "+ (finishTimeMls-startTimeMls) );
//        return result;
//    }
}
