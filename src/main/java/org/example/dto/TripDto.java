package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDto {
  @JsonProperty("route_id")
  public int routeId;
  @JsonProperty("trip_id")
  public String tripId;

}
