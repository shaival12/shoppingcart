package com.shop.cart.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * component to change the currency type after every 2 hours
 * @author shaival
 *
 */
@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
    
    @Scheduled(fixedRate = 7200000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Update the currency :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
       
        //code to request latest quote from Exchange and update
        ExchangeUtil.getCurrentRate();
        
    }

	
}