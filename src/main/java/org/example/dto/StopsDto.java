package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopsDto {
  @JsonProperty("stop_id")
  public int stopId;

  @JsonProperty("stop_name")
  public String stopName;
}
