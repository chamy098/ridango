package org.example.streaming.loaders;

import org.example.dto.StopTimesDto;
import org.example.streaming.StreamingLoader;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

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
    protected Path filePath() throws URISyntaxException {
        return Path.of(
            Objects.requireNonNull(
                    getClass()
                            .getClassLoader()
                            .getResource("data/stop_times.txt"),
                    "stop_times.txt not found on classpath"
            ).toURI()
    );
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
