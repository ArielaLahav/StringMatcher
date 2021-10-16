package stringMatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MatchDictionaryTest {
  @Test
  public void dictionaryFileNotExist_emptyDictionary() {
    MatchDictionary matchDictionary = new MatchDictionary("blabla");
    assertEquals(0, matchDictionary.getDictionary().size());
  }

  @Test
  public void dictionaryWithDuplicationsAndSpaces_dictionaryInitialized() {
    MatchDictionary matchDictionary = new MatchDictionary("./src/test/resources/smallDictionary");
    assertEquals(2, matchDictionary.getDictionary().size());
    assertTrue(matchDictionary.getDictionary().contains("A"));
    assertTrue(matchDictionary.getDictionary().contains("B"));
  }

}
