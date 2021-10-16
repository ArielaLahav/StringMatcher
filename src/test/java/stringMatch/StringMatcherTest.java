package stringMatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Multimap;
import java.util.Collection;
import org.junit.jupiter.api.Test;

public class StringMatcherTest {

  @Test
  public void dictionaryFileNotExist_successButWithNoMatch() {
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("blabla"));
    Multimap<String, Integer> match = stringMatcher.match("abcd");
    assertEquals(0, match.size());
  }

  @Test
  public void emptyDictionary_noMatch() {
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("./src/test/resources/emptyDictionary"));
    Multimap<String, Integer> match = stringMatcher.match("abcd");
    assertEquals(0, match.size());
  }

  @Test
  public void match_emptyStringToMatch_noMatch(){
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("./src/test/resources/dictionary"));
    Multimap<String, Integer> match = stringMatcher.match("");

    assertEquals(0, match.size());
  }

  @Test
  public void hasMatches_foundAllMatches(){
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("./src/test/resources/dictionary"));
    Multimap<String, Integer> match = stringMatcher.match("When Mark is going home with William. William is happy.");

    assertEquals(2, match.keySet().size());

    Collection<Integer> markPosition = match.get("Mark");
    assertEquals(1, markPosition.size());
    assertEquals(5, markPosition.iterator().next());

    Collection<Integer> williamPosition = match.get("William");
    assertEquals(2, williamPosition.size());
    assertTrue(williamPosition.contains(29));
    assertTrue(williamPosition.contains(38));
  }

  @Test
  public void dontMatchCase_noMatchesReturned(){
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("./src/test/resources/dictionary"));
    Multimap<String, Integer> match = stringMatcher.match("When mark is going home with william.");

    assertEquals(0, match.keySet().size());
  }

  @Test
  public void dontMatchFullWord_noMatchesReturned(){
    StringMatcher stringMatcher = new StringMatcher(new MatchDictionary("./src/test/resources/dictionary"));
    Multimap<String, Integer> match = stringMatcher.match("When Markus is going home with willi.");

    assertEquals(0, match.keySet().size());
  }

}
