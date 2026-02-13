package service;

import org.example.dto.RouteResultDto;
import org.example.service.RoutesService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class RoutesServiceTest {

  @Test
  void findRoutesShouldWork() throws IOException {
    final LocalTime currentTime = LocalTime.parse("12:00:00");
    RoutesService service = new RoutesService(currentTime, 2);
    RouteResultDto result = service.findRoutesForStop(2, 2);

    assertNotNull(result);
  }

  @Test
  void findRoutesForStop() throws IOException {
    final LocalTime currentTime = LocalTime.parse("12:00:00");
    RoutesService service = new RoutesService(currentTime, 2);
    RouteResultDto result = service.findRoutesForStop(2, 2);

    assertNotNull(result);
    assertEquals("AL Masjid Al-nabawi (Clock Roundabout)", result.stopName());
    assertTrue(result.routes().containsKey(101), "Route 101 should be present");
    assertTrue(result.routes().containsKey(106), "Route 106 should be present");
    assertTrue(result.routes().containsKey(107), "Route 107 should be present");

    assertEquals(2, result.routes().get(101).size(), "Route 101 should have 2 trips");
    assertEquals(2, result.routes().get(106).size(), "Route 106 should have 2 trips");
    assertEquals(2, result.routes().get(107).size(), "Route 107 should have 2 trips");
  }

  @Test
  void shouldThrowNoSuchObjectException() {
    final LocalTime currentTime = LocalTime.parse("01:00:00");
    RoutesService service = new RoutesService(currentTime, 2);

    assertThrows(
        NoSuchObjectException.class,
        () -> service.findRoutesForStop(2, 2)
    );

  }
}
