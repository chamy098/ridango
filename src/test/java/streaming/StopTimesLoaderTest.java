package streaming;

import org.example.dto.StopTimesDto;
import org.example.streaming.loaders.StopTimesLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StopTimesLoaderTest {

  private Map<String, StopTimesDto> getTripToStopsTest() throws IOException {
    LocalTime currentTime = LocalTime.of(12, 0);
    StopTimesLoader loader = new StopTimesLoader(2, currentTime,2) ;
    loader.load();

    return loader.getTripToStops();
  }

  @Test
  void shouldReturnTripAfterCurrentTime() throws Exception {
    Map<String, StopTimesDto> tripToStops = getTripToStopsTest();

    assertFalse(tripToStops.containsKey("NORMAL_03_101_Return_22:10"), "Trip NORMAL_03_101_Return_22:10 should NOT be present");
  }

  @Test
  void shouldNotReturnTripBeforeCurrentTime() throws Exception {
    Map<String, StopTimesDto> tripToStops = getTripToStopsTest();

    assertTrue(tripToStops.containsKey("NORMAL_03_106_Return_12:40"), "Trip NORMAL_03_106_Return_12:40 should be present");
  }

}
