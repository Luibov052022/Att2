import java.io.IOException;

public class main {

  public static void main(String[] args) throws IOException {
    Game.init();
    while (true) {
      Game.choice();
      Game.printMenu();
    }
  }
}
