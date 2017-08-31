package com.shutdownsforcityelf;

import com.shutdownsforcityelf.repository.ElectricityForecastRepository;
import com.shutdownsforcityelf.repository.GasForecastRepository;
import com.shutdownsforcityelf.repository.WaterForecastRepository;
import com.shutdownsforcityelf.service.DataCollectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@EnableScheduling
public class ScheduledTasks {

  private final int hours = 24;

  @Autowired
  private DataCollectorService dataCollectorService;

  @Autowired
  private WaterForecastRepository waterForecastRepository;

  @Autowired
  private ElectricityForecastRepository electricityForecastRepository;

  @Autowired
  private GasForecastRepository gasForecastRepository;

  @Scheduled(initialDelay = 10000, fixedRate = 1800000)
  public void startCollector() {
    System.out.println("\nParser Collector START!!!");
    dataCollectorService.startCollector();
    System.out.println("\nParser Collector END!!!");
  }

  @Scheduled(cron = "0 1 * * *")
  public void startCleaner() {
    System.out.println("\nCLEANER START!!!");
    waterForecastRepository.deleteByStartBefore(LocalDateTime.now().minusHours(hours));
    electricityForecastRepository.deleteByStartBefore(LocalDateTime.now().minusHours(hours));
    gasForecastRepository.deleteByStartBefore(LocalDateTime.now().minusHours(hours));
    System.out.println("\nCLEANER END!!!");
  }
}
