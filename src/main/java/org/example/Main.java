package org.example;

import org.example.dto.UserPrompt;
import org.example.service.RoutesService;
import org.example.utils.ArgumentValidator;
import org.example.utils.TimeUtil;

import java.time.LocalTime;

public class Main {
  public static void main(String[] args) {
    UserPrompt userPrompt = ArgumentValidator.validateAndParse(args);
    final LocalTime currentTime = LocalTime.now();
    RoutesService routesService = new RoutesService(currentTime,2);
    try {
      var result = routesService.findRoutesForStop(userPrompt.stationId(), userPrompt.num_buses_per_line());

      if(result.routes().isEmpty()) return;

      System.out.println("Stop name: " + result.stopName());

      result.routes().forEach((routeId, times) -> {
        System.out.println("Route ID: " + routeId);
        times.forEach(time ->
            System.out.println("  Arrival: " + TimeUtil.formatArrivalTime(userPrompt.timeFormat(),currentTime, time )));
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}