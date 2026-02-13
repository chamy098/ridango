package org.example.service;

import org.example.dto.RouteResultDto;
import org.example.dto.StopTimesDto;
import org.example.streaming.loaders.StopLoader;
import org.example.streaming.loaders.StopTimesLoader;
import org.example.streaming.loaders.TripLoader;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableSet;

public class RoutesService {
  LocalTime currentTime;
  int duration;

  public RoutesService(LocalTime currentTime, int duration) {
    this.currentTime = currentTime;
    this.duration = duration;
  }

  public RouteResultDto findRoutesForStop(int stationId, int maxPerRoute) throws IOException {
    // Load stop times for the given station ID and filter for the next 2 hours
    StopTimesLoader loader = new StopTimesLoader(stationId,currentTime, duration);
    loader.load();

    Map<String, StopTimesDto> tripToStops = loader.getTripToStops();
    if(tripToStops.isEmpty()) {
      throw new NoSuchObjectException("No routes found for stop_id=" + stationId + " in the next 2 hours");
    }

    // Load trips that correspond to the filtered stop times and organize them by route
    TripLoader tripLoader = new TripLoader(tripToStops, maxPerRoute);
    tripLoader.load();
    Map<Integer, NavigableSet<LocalTime>> routes = tripLoader.getRoutes();

    // Load the stop name for the given station ID
    StopLoader stopLoader = new StopLoader(stationId);
    stopLoader.load();
    String stopName = stopLoader.getFoundStop().stopName;

    return new RouteResultDto(stopName, routes);

  }
}
