package org.example.utils;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
  public static String formatArrivalTime(String timeFormat, LocalTime currentTime, LocalTime arrivalTime) {
    return switch (timeFormat) {
      case "absolute" -> arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"));
      case "relative" -> {
        long minutesUntilArrival = Duration.between(currentTime, arrivalTime).toMinutes();
        yield minutesUntilArrival + " min";
      }
      default -> throw new IllegalArgumentException("Invalid time format: " + timeFormat);
    };
  }
}
