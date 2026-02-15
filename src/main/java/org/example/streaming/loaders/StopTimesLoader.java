package org.example.streaming.loaders;

import org.example.dto.StopTimesDto;
import org.example.streaming.StreamingLoader;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopTimesLoader extends StreamingLoader<StopTimesDto> {
    private final int paramStopId;
    private final LocalTime currentTime;
    private final LocalTime maxTime;
    private final Map<String, StopTimesDto> tripToStops = new HashMap<>();

  public StopTimesLoader(int paramStopId, LocalTime currentTime, int duration) {
    this.paramStopId = paramStopId;
    this.currentTime = currentTime;
    this.maxTime = currentTime.plusHours(duration);
  }

  @Override
    protected Class<StopTimesDto> dtoClass() {
        return StopTimesDto.class;
    }

    @Override
    protected String resourcePath() {
        return "data/stop_times.txt";
    }

    @Override
    protected void processBatch(List<StopTimesDto> batch) {
         batch.stream()
                .filter(s -> s.stopId == paramStopId)
                .filter(s -> s.arrivalTime.isAfter(currentTime))
                .filter(s -> s.arrivalTime.isBefore(maxTime))
                 .forEach(s ->
                         tripToStops.put(s.tripId, s)
                 );

    }

    public Map<String,StopTimesDto> getTripToStops() {
        return tripToStops;
    }
}
