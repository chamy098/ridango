package org.example.streaming.loaders;

import org.example.dto.StopsDto;
import org.example.streaming.StreamingLoader;
import java.util.List;

public class StopLoader extends StreamingLoader<StopsDto> {
  private final int paramStopId;
  private StopsDto foundStop;

  public StopLoader(int paramStopId) {
    this.paramStopId = paramStopId;
  }
  @Override
  protected Class<StopsDto> dtoClass() {
    return StopsDto.class;
  }

  @Override
  protected String resourcePath() {
    return "data/stops.txt";
  }

  @Override
  protected void processBatch(List<StopsDto> batch) {
    if(foundStop != null) {
      return;
    }
    batch.stream()
        .filter(s -> s.stopId == paramStopId)
        .findFirst()
        .ifPresent(s -> foundStop = s);;
  }

  public StopsDto getFoundStop() {
    return foundStop;
  }
}
