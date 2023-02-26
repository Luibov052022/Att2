import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Game {

  public static List<Toy> game;
  public static List<Toy> prizes;

  public static void init() {
    game = new ArrayList<>();
    Toy g1 = new Toy("Barbie", 15);
    Toy g2 = new Toy("Car", 50);
    Toy g3 = new Toy("Constructor", 75);
    Toy g4 = new Toy("g4", 1);
    Toy g5 = new Toy("g5", 5);
    Toy g6 = new Toy("g6", 6);
    Toy g7 = new Toy("g7", 7);
    Toy g8 = new Toy("g8", 8);
    game.add(g1);
    game.add(g2);
    game.add(g3);
    game.add(g4);
    game.add(g5);
    game.add(g6);
    game.add(g7);
    game.add(g8);
    printList(game);
    printMenu();
  }

  public static void printMenu() {
    System.out.println(" ");
    System.out.println("Меню");
    System.out.println(" ");
    System.out.println("1. Добавить  игрушку");
    System.out.println("2. Изменить процент выпадения");
    System.out.println("3. Разыграть призы");
    System.out.println("4. Получить приз");
    System.out.println("5. Выход");
  }

  public static void choice() throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите пункт меню: ");
    int number = sc.nextInt();

    switch (number) {
      case 1 -> addToy();
      case 2 -> setPercent(game);
      case 3 -> raffle(game);
      case 4 -> getPrize(prizes);
      case 5 -> System.exit(0);
      default -> System.out.println("Неверный ввод!");
    }
  }

  public static void addToy() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите название игрушки: ");
    String name = sc.nextLine();
    System.out.println("Введите процент выпадения от 0 до 100: ");
    int percent = sc.nextInt();
    if (percent > 0 && percent < 101) {
      Toy t = new Toy(name, percent);
      game.add(t);
      printList(game);
    } else {
      System.out.println("Неверный ввод процента выпадения!");
    }
  }

  public static void printList(List<Toy> list) {
    for (Toy i : list) {
      System.out.println(i.getInfo());
    }
  }

  public static void raffle(List<Toy> list) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите количество призов: ");
    int count = sc.nextInt();
    PriorityQueue<Toy> queue = new PriorityQueue<>();
    for (Toy i : list) {
      queue.add(i);
    }
    Toy currentToy = null;
    prizes = new ArrayList<>();
    int i = 0;
    while ((currentToy = queue.poll()) != null && i < count) {
      prizes.add(currentToy);
      i++;
    }
    System.out.println("РОЗЫГРЫШ");
    printList(prizes);
  }

  public static void setPercent(List<Toy> list) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите ID игрушки для изменения: ");
    int ID = sc.nextInt();
    System.out.println("Введите процент выпадения: ");
    int percent = sc.nextInt();
    if (percent > 0 && percent < 101) {
      for (Toy i : list) {
        if (i.ID == ID) {
          i.percent = percent;
        }
      }
      printList(list);
    } else {
      System.out.println("Неверный ввод процента выпадения!");
    }
  }

  public static void getPrize(List<Toy> list) throws IOException {
    try {
      writeFile(list.get(0));
      list.remove(0);
      printList(list);
    } catch (NullPointerException e) {
      System.out.println("Список призов пуст!");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Список призов пуст!");
    }
  }

  public static void writeFile(Toy c) throws IOException {
    try (FileWriter w = new FileWriter("prizes.txt", true)) {
      w.write(c.getInfo() + "\n");
    } catch (Exception e) {
      System.out.println("Проблема с файлом");
    }
  }
}
