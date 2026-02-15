package streaming;

import org.example.streaming.loaders.StopLoader;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StopLoaderTest {

  @Test
  void testStopFound() throws Exception {
    StopLoader loader = new StopLoader(2);

    loader.load();

    assertNotNull(loader.getFoundStop(), "Stop should be found");
    assertEquals(2, loader.getFoundStop().stopId);
    assertEquals("AL Masjid Al-nabawi (Clock Roundabout)", loader.getFoundStop().stopName);
  }

  @Test
  void testStopNotFound() throws Exception {
    StopLoader loader = new StopLoader(100);
    loader.load();
    assertNull(loader.getFoundStop(), "Stop should not be found");
  }

}
