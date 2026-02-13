package org.example.dto;

import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableSet;

public record RouteResultDto(String stopName, Map<Integer, NavigableSet<LocalTime>> routes) {
}
