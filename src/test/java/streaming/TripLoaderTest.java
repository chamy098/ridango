package streaming;

import org.example.dto.StopTimesDto;
import org.example.streaming.loaders.TripLoader;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;
import java.util.NavigableSet;
import static org.junit.jupiter.api.Assertions.*;

public class TripLoaderTest {

  private Map<Integer, NavigableSet<LocalTime>> getTestRoutes(Map<String, StopTimesDto> tripToStops, int numBusesPerLine) throws IOException {
    TripLoader loader = new TripLoader(tripToStops, numBusesPerLine) ;
    loader.load();

    return loader.getRoutes();
  }

  @Test
  void testTripLoader() throws Exception {
    Map<String, StopTimesDto> tripToStops = Map.of(
        "NORMAL_03_101_Return_22:10", new StopTimesDto("NORMAL_03_101_Return_22:10", LocalTime.of(8, 0), 1),
        "NORMAL_03_103_Go_07:20", new StopTimesDto("NORMAL_03_103_Go_07:20", LocalTime.of(9, 0), 2),
        "NORMAL_03_101_Go_17:20", new StopTimesDto("NORMAL_03_101_Go_17:20", LocalTime.of(10, 0), 3)
    );

    Map<Integer, NavigableSet<LocalTime>> routes = getTestRoutes(tripToStops,2);

    assertTrue(routes.containsKey(101), "Route 101 should be present");
    assertTrue(routes.containsKey(103), "Route 103 should be present");
    assertEquals(2, routes.get(101).size(), "Route 101 should have 2 trips");
    assertEquals(1, routes.get(103).size(), "Route 103 should have 1 trip");
  }

  @Test
  void routesWillNotHaveMoreThanMaxBuses() throws Exception {
    Map<String, StopTimesDto> tripToStops = Map.of(
        "NORMAL_03_101_Return_22:10", new StopTimesDto("NORMAL_03_101_Return_22:10", LocalTime.of(8, 0), 1),
        "NORMAL_03_103_Go_07:20", new StopTimesDto("NORMAL_03_103_Go_07:20", LocalTime.of(9, 0), 2),
        "NORMAL_03_101_Go_17:20", new StopTimesDto("NORMAL_03_101_Go_17:20", LocalTime.of(10, 0), 3)
    );

    Map<Integer, NavigableSet<LocalTime>> routes = getTestRoutes(tripToStops, 1);

    assertTrue(routes.containsKey(101), "Route 101 should be present");
    assertTrue(routes.containsKey(103), "Route 103 should be present");
    assertEquals(1, routes.get(101).size(), "Route 101 should have only 1 trip due to max limit");
    assertEquals(1, routes.get(103).size(), "Route 103 should have 1 trip");
  }
}
