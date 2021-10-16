package stringMatch;

import com.google.common.collect.Multimap;
import com.jramoyo.io.IndexedFileReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class StringMatchExecutor {

  private final int batchSize = 1000;
  private final StringMatcher stringMatcher;

  public StringMatchExecutor(String dictionaryFileName) {
    MatchDictionary matchDictionary = new MatchDictionary(dictionaryFileName);
    stringMatcher = new StringMatcher(matchDictionary);
  }

  public void execute(String fileName) throws IOException {
    File f = new File(fileName);
    IndexedFileReader indexedFileReader = new IndexedFileReader(f);
    StringMatchAggregator aggregator = new StringMatchAggregator();
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    try {
      for (int i = 0; i < indexedFileReader.getLineCount(); i += batchSize) {
        int from = i==0 ? 1 : i;
        int to = i + batchSize - 1;
        Multimap<String, Integer> keyToCharOffset = executor.submit(() ->
            executeMatcher(indexedFileReader, from, to)
        ).get();

        aggregator.addMatches(from, keyToCharOffset);
      }

    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }

    aggregator.printMatches();

  }

  private Multimap<String, Integer> executeMatcher(IndexedFileReader indexedFileReader, int from, int to) throws IOException {
    String batch = String.join("", indexedFileReader.readLines(from, to).values());
    return stringMatcher.match(batch);
  }

}
