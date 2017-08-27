package com.shutdownsforcityelf;

import com.shutdownsforcityelf.service.DataCollectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledTasks {

  @Autowired
  private DataCollectorService dataCollectorService;

  @Scheduled(initialDelay = 10000, fixedRate = 1800000)
  public void startCollector() {
    System.out.println("\nParser Collector START!!!");
    dataCollectorService.startCollector();
    System.out.println("\nParser Collector END!!!");
  }
}
