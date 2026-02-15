package org.example.streaming;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.MappingIterator;

// AI generated
public abstract class StreamingLoader<T> {

  protected abstract Class<T> dtoClass();
  protected abstract String resourcePath();
  protected abstract void processBatch(List<T> batch);

  private static final int BATCH_SIZE = 1000;

  public void load() throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = CsvSchema.emptySchema().withHeader();

    try (
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath());
        MappingIterator<T> it =
            mapper.readerFor(dtoClass())
                .with(schema)
                .readValues(is)
    ) {
      List<T> batch = new ArrayList<>(BATCH_SIZE);

      while (it.hasNext()) {
        batch.add(it.next());

        if (batch.size() == BATCH_SIZE) {
          processBatch(batch);
          batch.clear();
        }
      }

      if (!batch.isEmpty()) {
        processBatch(batch);
      }
    }
  }
}
