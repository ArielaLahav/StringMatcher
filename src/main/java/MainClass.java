import java.io.IOException;
import stringMatch.StringMatchExecutor;

public class MainClass {

  public static void main(String[] args) throws IOException {
    StringMatchExecutor executor = new StringMatchExecutor("./src/main/resources/dictionary");
    executor.execute("./src/main/resources/big");
  }

}
