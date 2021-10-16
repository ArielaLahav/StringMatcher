package stringMatch;

import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class MatchDictionary {

  private final Set<String> dictionary = Sets.newHashSet();

  public MatchDictionary(String dictionaryFileName) {
    try {
      Scanner scanner = new Scanner(new File(dictionaryFileName));
      while (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        if (nextLine.length() == 0) {
          continue;
        }
        dictionary.add(nextLine.trim());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Set<String> getDictionary() {
    return dictionary;
  }

}
