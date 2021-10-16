package stringMatch;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringMatchAggregator {

  private static final Multimap<String, MatchOffset> matches = Multimaps.synchronizedMultimap(HashMultimap.create());

  public void addMatches(int lineOffset, Multimap<String, Integer> newMatches) {
    newMatches
        .keys()
        .stream()
        .distinct()
        .forEach(key ->
            matches.putAll(key, newMatches.get(key).stream()
                .map(charOffset -> new MatchOffset(lineOffset, charOffset))
                .collect(Collectors.toList()))
        );
  }

  public void printMatches() {
    matches
        .keys()
        .stream()
        .distinct()
        .forEach(key ->
            System.out.println(key + "--> [" + Arrays.toString(matches.get(key).toArray()) + "]")
        );
  }

}
