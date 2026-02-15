package org.example.utils;

import org.example.dto.UserPrompt;

public class ArgumentValidator {

  public static UserPrompt validateAndParse(String[] args) {
    if(args.length != 3) {
      throw new IllegalArgumentException("Expected 3 arguments: <station_id> <num_buses_per_line> <relative|absolute>");
    }
    int stationId = Integer.parseInt(args[0]);
    int maxBuses = Integer.parseInt(args[1]);
    String timeFormat = args[2];

    if(!timeFormat.equals("absolute") && !timeFormat.equals("relative")) {
      throw new IllegalArgumentException("Invalid time format: " + timeFormat);
    }

    return new UserPrompt(stationId, maxBuses, timeFormat);
  }
}
