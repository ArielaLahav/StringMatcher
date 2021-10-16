package stringMatch;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringMatchExecutorTest {

  @Test
  public void hasMatches_foundAllMatches() throws Exception {
    StringMatchExecutor executor = new StringMatchExecutor("./src/test/resources/dictionary");

    String text = tapSystemOut(() -> {
      executor.execute("./src/test/resources/big");
    });

    String[] split = text.replaceAll("\r\n", "").split("]]]", 40);

    assertEquals(37, split.length);

    String gregoryMatch = null;
    String edwardMatch = null;
    for (String match : split) {
      if (match.startsWith("Gregory")) {
        gregoryMatch = match;
      } else if (match.startsWith("Edward")) {
        edwardMatch = match;
      }
    }

    assertNotNull(gregoryMatch);
    assertNotNull(edwardMatch);

    assertTrue(gregoryMatch.contains("lineOffset=29000, charOffset=24227"));
    assertTrue(gregoryMatch.contains("lineOffset=54000, charOffset=10794"));
    assertTrue(edwardMatch.contains("lineOffset=1, charOffset=69815"));
    assertTrue(edwardMatch.contains("lineOffset=4000, charOffset=106953"));
    assertTrue(edwardMatch.contains("lineOffset=28000, charOffset=7634"));
    assertTrue(edwardMatch.contains("lineOffset=10000, charOffset=19145"));
    assertTrue(edwardMatch.contains("lineOffset=17000, charOffset=10470"));
    assertTrue(edwardMatch.contains("lineOffset=19000, charOffset=50716"));

  }

}
