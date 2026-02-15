package utils;

import org.example.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeUtilTest {

  @Test
  void shouldFormatWithAbsolute() {
    LocalTime currentTime = LocalTime.parse("01:00:00");
    LocalTime arrivalTime = LocalTime.parse("01:30:00");
    String result = TimeUtil.formatArrivalTime("absolute" , currentTime,arrivalTime);

    assert result.equals("01:30");
  }

  @Test
  void shouldFormatWithRelative() {
    LocalTime currentTime = LocalTime.parse("01:00:00");
    LocalTime arrivalTime = LocalTime.parse("01:30:00");
    String result = TimeUtil.formatArrivalTime("relative" , currentTime,arrivalTime);

    assert result.equals("30 min");
  }

  @Test
  void shouldThrowIllegalArgumentExceptionIfFormatInvalid() {
    LocalTime currentTime = LocalTime.parse("01:00:00");
    LocalTime arrivalTime = LocalTime.parse("01:30:00");
    assertThrows(
        IllegalArgumentException.class,
        () -> TimeUtil.formatArrivalTime("invalid-format" , currentTime,arrivalTime)
    );
  }
}
