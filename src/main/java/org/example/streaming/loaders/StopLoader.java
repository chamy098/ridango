package org.example.streaming.loaders;

import org.example.dto.StopsDto;
import org.example.streaming.StreamingLoader;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

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
  protected Path filePath() throws URISyntaxException {
    return Path.of(
        Objects.requireNonNull( getClass()
            .getClassLoader()
            .getResource("data/stops.txt")
        ).toURI());
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
