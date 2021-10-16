package stringMatch;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;

public class StringMatcher {

  private final Trie trie;

  public StringMatcher(MatchDictionary matchDictionary) {
    TrieBuilder builder = Trie.builder();
    matchDictionary.getDictionary().forEach(builder::addKeyword);
    trie = builder.onlyWholeWords().build();
  }

  public Multimap<String, Integer> match(String parsedText) {
    Multimap<String, Integer> keyToCharOffset = HashMultimap.create();
    trie.parseText(parsedText)
        .forEach(emit -> keyToCharOffset.put(emit.getKeyword(), emit.getStart()));
    return keyToCharOffset;
  }
}
