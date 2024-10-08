package br.com.coletor.shipay.infrastructure.integration.cron;


import br.com.coletor.shipay.application.service.ShipayColectorApplicationService;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;


@Component
public class SchedulerIntegrationJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerIntegrationJob.class);

  @Autowired
  ShipayColectorApplicationService shipayService;

  @Value("${scheduler.thread.pool.size:5}")
  private Integer poolSize;

  @PostConstruct
  public void runOnStartup() {
    LOGGER.info("ROOT - Initial run of Shipay Transaction Colector");
    executeJob();
  }

  @Scheduled(cron = "${scheduler.cron}")
  public void scheduleTaskWithCronExpression() {
    LOGGER.info("ROOT - Scheduled run of Shipay Transaction Colector");
    executeJob();
  }

  private void executeJob() {
    LOGGER.info("ROOT - Job started");
    shipayService.getPixData().subscribe();
  }

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(poolSize);
    taskScheduler.setThreadNamePrefix("ScheduledTask-");
    return taskScheduler;
  }

}
