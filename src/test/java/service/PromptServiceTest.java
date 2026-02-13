package service;

import org.example.dto.UserPrompt;
import org.example.service.PromptService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.rmi.NoSuchObjectException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PromptServiceTest {

  @Test
  void promptUserShouldWork() {
    String input = "5\n10\nrelative\n";
    PromptService service = new PromptService(new ByteArrayInputStream(input.getBytes()));
    UserPrompt result = service.promptUser();

    assertEquals(5, result.stationId());
    assertEquals(10, result.num_buses_per_line());
    assertEquals("relative", result.timeFormat());
  }

  @Test
  void shouldThrowIllegalArgumentException() {
    String input = "5\n10\ninvalid_format\n";
    PromptService service = new PromptService(new ByteArrayInputStream(input.getBytes()));
    assertThrows(
        IllegalArgumentException.class,
        service::promptUser
    );
  }
}
