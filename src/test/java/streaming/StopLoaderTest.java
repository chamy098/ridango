package streaming;

import fixtures.DataFixture;
import org.example.streaming.loaders.StopLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;

public class StopLoaderTest {
  private Path tempCsv;

  @BeforeEach
  void setUp() throws IOException {
    tempCsv = Files.createTempFile("stops", ".txt");
    Files.writeString(tempCsv, DataFixture.STOPS_CSV);
  }

  @Test
  void testStopFound() throws Exception {
    StopLoader loader = new StopLoader(2) {
      @Override
      protected Path filePath() {
        return tempCsv;
      }
    };

    loader.load();

    assertNotNull(loader.getFoundStop(), "Stop should be found");
    assertEquals(2, loader.getFoundStop().stopId);
    assertEquals("AL Masjid Al-nabawi (Clock Roundabout)", loader.getFoundStop().stopName);
  }

  @Test
  void testStopNotFound() throws Exception {
    StopLoader loader = new StopLoader(100) {
      @Override
      protected Path filePath() {
        return tempCsv;
      }
    };
    loader.load();
    assertNull(loader.getFoundStop(), "Stop should not be found");
  }

}
