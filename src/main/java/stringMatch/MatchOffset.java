package stringMatch;

public class MatchOffset {

  private final int lineOffset;
  private final int charOffset;

  public MatchOffset(int lineOffset, int charOffset) {
    this.lineOffset = lineOffset;
    this.charOffset = charOffset;
  }

  @Override
  public String toString() {
    return "[lineOffset=" + lineOffset + ", charOffset=" + charOffset + "]";
  }

}
