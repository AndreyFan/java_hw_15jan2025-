package de.telran.onlineshop.scheduled;

import de.telran.onlineshop.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ScheduledService {

    @Autowired
    private ProductsRepository productsRepository;
    @Scheduled(fixedDelay = 2000)  //
    @SchedulerLock(name = "scheduledTask")
    public void scheduledTask() throws InterruptedException {
        log.error("Пример работы ScheduledTAsk -> "+ LocalDateTime.now());
        Thread.sleep(3000);
    }
}
