package utils;

import org.example.dto.UserPrompt;
import org.example.utils.ArgumentValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentValidatorTest {

  @Test
  void promptUserShouldWork() {
    String[] args = {"5", "10", "relative"};
    UserPrompt result = ArgumentValidator.validateAndParse(args);

    assertEquals(5, result.stationId());
    assertEquals(10, result.num_buses_per_line());
    assertEquals("relative", result.timeFormat());
  }

  @Test
  void shouldThrowIllegalArgumentException() {
    String[] args = {"5", "10", "invalid_format"};
    assertThrows(
        IllegalArgumentException.class,
        () -> ArgumentValidator.validateAndParse(args)
    );
  }

  @Test
  void shouldThrowIllegalArgumentExceptionIfArgsLengthInvalid() {
    String[] args = {"5", "10"};
    assertThrows(
        IllegalArgumentException.class,
        () -> ArgumentValidator.validateAndParse(args)
    );
  }
}
