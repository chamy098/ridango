package org.example.streaming.loaders;


import org.example.dto.StopTimesDto;
import org.example.dto.TripDto;
import org.example.streaming.StreamingLoader;
import java.time.LocalTime;
import java.util.*;


public class TripLoader extends StreamingLoader<TripDto> {
  private final Map<String,StopTimesDto> tripToStops;
  private final int num_buses_per_line;
  private final Map<Integer, NavigableSet<LocalTime>> routes = new HashMap<>();

  public TripLoader(Map<String,StopTimesDto> tripToStops, int num_buses_per_line) {
    this.tripToStops = tripToStops;
    this.num_buses_per_line = num_buses_per_line;
  }

  @Override
  protected Class<TripDto> dtoClass() {
    return TripDto.class;
  }

  @Override
  protected String resourcePath() {
    return "data/trips.txt";
  }

  @Override
  protected void processBatch(List<TripDto> batch) {
    List<TripDto> filtered = batch.stream()
        .filter(t -> tripToStops.containsKey(t.tripId))
        .toList();

    filtered.forEach(s ->
        addToRoutes(s, tripToStops.get(s.tripId).arrivalTime, num_buses_per_line)
    );
  }

  private void addToRoutes(TripDto trip, LocalTime arrivalTime, int maxRoutesPerStop) {
    if(routes.containsKey(trip.routeId) && routes.get(trip.routeId).size() >= maxRoutesPerStop) {
      return;
    }
    routes.computeIfAbsent(trip.routeId, k -> new TreeSet<>()).add(arrivalTime);
  }

  public Map<Integer, NavigableSet<LocalTime>> getRoutes() {
    return routes;
  }
}
