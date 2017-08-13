package com.shutdownsforcityelf.service;

import com.shutdownsforcityelf.exceptions.AddressException;
import com.shutdownsforcityelf.exceptions.AddressNotPresentException;
import com.shutdownsforcityelf.model.Address;
import com.shutdownsforcityelf.model.ElectricityForecast;
import com.shutdownsforcityelf.model.GasForecast;
import com.shutdownsforcityelf.model.WaterForecast;
import com.shutdownsforcityelf.repository.ElectricityForecastRepository;
import com.shutdownsforcityelf.repository.GasForecastRepository;
import com.shutdownsforcityelf.repository.WaterForecastRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShutdownsInfoService {

  @Autowired
  private WaterForecastRepository waterForecastRepository;

  @Autowired
  private GasForecastRepository gasForecastRepository;

  @Autowired
  private ElectricityForecastRepository electricityForecastRepository;

  @Autowired
  private AddressService addressService;

  public List<Map<String, Object>> getAllForecasts(String address) throws AddressException {
    Address addressFound = addressService.getAddress(address)
        .orElseThrow(() -> new AddressNotPresentException());
    List<WaterForecast> forecastWater = waterForecastRepository.findByAddress(addressFound);
    List<ElectricityForecast> forecastElectricity = electricityForecastRepository
        .findByAddress(addressFound);
    List<GasForecast> forecastGas = gasForecastRepository
        .findByAddress(addressFound);

    List<Map<String, Object>> forecasts = new ArrayList<>();

    for (Object f : forecastWater) {
      Map<String, Object> forecastMap = new HashMap<>();
      forecastMap.put("Water", f);
      forecasts.add(forecastMap);
    }

    for (Object f : forecastElectricity) {
      Map<String, Object> forecastMap = new HashMap<>();
      forecastMap.put("Electricity", f);
      forecasts.add(forecastMap);
    }

    for (Object f : forecastGas) {
      Map<String, Object> forecastMap = new HashMap<>();
      forecastMap.put("Gas", f);
      forecasts.add(forecastMap);
    }

    return forecasts;
  }
}
