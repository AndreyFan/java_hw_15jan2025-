package de.telran.onlineshop.aspect.log;


import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static final Logger logController = LoggerFactory.getLogger(LoggingAspect.class);
    private static final Logger logService = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("execution(* de.telran.onlineshop.controller..*(..)))")
    public Object mdcServiceController(@NotNull final ProceedingJoinPoint joinPoint) throws Throwable {
        String queryMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logBeforeControllerQuery(queryMethod, args);
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            logAfterControllerQuery(queryMethod, args, result, startTime);
            return result;
        } catch (Exception ex) {
            logAndGetErrorMessage(queryMethod, args, ex, startTime);
            throw ex;
        }
    }

    @Around("execution(* de.telran.onlineshop.service..*(..))")
    public Object makerLogAllService(@NotNull final ProceedingJoinPoint pjp) throws Throwable {
        Long startTimeMls = System.currentTimeMillis();
        Timestamp startTime = new Timestamp(startTimeMls);
        String argsAsString = Arrays.toString(pjp.getArgs());
        logService.info("!!! befor !!! " + " время старта метода: " + startTime + pjp.getSignature().getName() + "  args={ " + argsAsString + " };");

        Object result = pjp.proceed(); //запуск перехваченного метода

        Long finishTimeMls = System.currentTimeMillis();
        Timestamp finishTime = new Timestamp(finishTimeMls);
        String resAsString = result.toString();
        logService.info("!!! after !!! " + " время окончания работы метода: " + finishTime + " Результат: " + resAsString + " отработал за:  "+ (finishTimeMls-startTimeMls) );
        return result;
    }

    private void logBeforeControllerQuery(final String queryMethod, final Object[] args) {
        MDCFields.CONTROLLER_STEP.putMdcField("CONTROLLER_IN");
        MDCFields.CONTROLLER_METHOD.putMdcFieldWithFieldName(queryMethod);
        String argsAsString = Arrays.toString(args);
        logController.info("args={};", argsAsString);
        MDCFields.CONTROLLER_METHOD.removeMdcField();
        MDCFields.CONTROLLER_STEP.removeMdcField();
    }

    private void logAfterControllerQuery(final String queryMethod, final Object[] args, final Object result, final long startTime) {
        long callTime = System.currentTimeMillis() - startTime;
        String resultInfo = LogUtils.getDaoResultLogInfo(logController, result);
        MDCFields.CONTROLLER_STEP.putMdcField("CONTROLLER_OUT");
        MDCFields.CONTROLLER_METHOD.putMdcFieldWithFieldName(queryMethod);
        MDCFields.CONTROLLER_TIME.putMdcFieldWithFieldName(callTime);
        String argsAsString = Arrays.toString(args);
        logController.info(
                "args={}; RESULT: [{}]",
                argsAsString,
                resultInfo
        );
        MDCFields.CONTROLLER_TIME.removeMdcField();
        MDCFields.CONTROLLER_METHOD.removeMdcField();
        MDCFields.CONTROLLER_STEP.removeMdcField();
    }

    private void logAndGetErrorMessage(final String queryMethod, final Object[] args, final Exception ex, final long startTime) {
        long callTime = System.currentTimeMillis() - startTime;
        String errorMsg = String.format(
                "args=%s;",
                Arrays.toString(args)
        );

        MDCFields.CONTROLLER_STEP.putMdcField("CONTROLLER_ERROR");
        MDCFields.CONTROLLER_METHOD.putMdcFieldWithFieldName(queryMethod);
        MDCFields.CONTROLLER_TIME.putMdcFieldWithFieldName(callTime);
        logController.error(errorMsg, ex);
        MDCFields.CONTROLLER_TIME.removeMdcField();
        MDCFields.CONTROLLER_METHOD.removeMdcField();
        MDCFields.CONTROLLER_STEP.removeMdcField();
        throw new LogException(ex.getMessage(), ex);
    }

}
