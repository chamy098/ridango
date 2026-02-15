# Ridango - Bus Routes Finder

A Java application that helps you find upcoming bus arrivals at a specific transit stop.

## Overview

Ridango is a command-line tool that loads public transit data (stops, trips, and stop times) and allows you to query which buses are arriving at a specific stop, showing either absolute or relative arrival times.


## Building the Project

### Build JAR File

To build an executable JAR file with all dependencies included, run:

```bash
./gradlew build
```

The compiled JAR will be created at: `build/libs/busTrips.jar`

### Alternative - Clean Build

To perform a clean build (removes previous build artifacts first):

```bash
./gradlew clean build
```

## Running the Application

The application requires three command-line arguments:

```bash
java -jar build/libs/busTrips.jar <station_id> <num_buses_per_line> <time_format>
```

### Arguments

- **`station_id`**: The ID of the transit stop (integer)
- **`num_buses_per_line`**: Maximum number of buses to display per route (integer)
- **`time_format`**: How to display arrival times - either `relative` or `absolute`
  - `relative`: Shows time until the bus arrives (e.g., "in 5 minutes")
  - `absolute`: Shows the actual arrival time (e.g., "14:30")

### Example Usage

```bash
# Show buses arriving at stop 5, displaying 3 buses per route with relative times
java -jar build/libs/busTrips.jar 5 3 relative

# Show buses arriving at stop 10, displaying 2 buses per route with absolute times
java -jar build/libs/busTrips.jar 10 2 absolute
```
