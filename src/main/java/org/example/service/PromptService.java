package org.example.service;

import org.example.dto.UserPrompt;

import java.io.InputStream;
import java.util.Scanner;

public class PromptService {
  private final Scanner scanner;

  public PromptService(InputStream inputStream) {
    this.scanner = new Scanner(inputStream);
  }
  public UserPrompt promptUser() {

    System.out.print("Enter station id: ");
    int stationId = scanner.nextInt();

    System.out.print("Enter max buses per line: ");
    int maxBuses = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Enter time format (absolute/relative): ");
    String timeFormat =  scanner.nextLine();

    if(!timeFormat.equals("absolute") && !timeFormat.equals("relative")) {
      throw new IllegalArgumentException("Invalid time format: " + timeFormat);
    }

    return new UserPrompt(stationId, maxBuses, timeFormat);
  }
}
