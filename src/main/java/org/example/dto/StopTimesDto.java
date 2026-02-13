package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.streaming.deserializers.LocalTimeDeserializer;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopTimesDto {
  @JsonProperty("trip_id")
  public String tripId;
  @JsonDeserialize(using = LocalTimeDeserializer.class)
  @JsonProperty("arrival_time")
  public LocalTime arrivalTime;
  @JsonProperty("stop_id")
  public int stopId;

  public StopTimesDto() {}
  public StopTimesDto(String tripId, LocalTime arrivalTime, int stopId) {
    this.tripId = tripId;
    this.arrivalTime = arrivalTime;
    this.stopId = stopId;
  }
}
