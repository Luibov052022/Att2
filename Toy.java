public class Toy implements Comparable<Toy> {

  protected int ID = 0;
  protected String name;
  protected static int count = 1;
  protected int percent;

  public Toy(String name, int percent) {
    this.name = name;
    this.percent = percent;
    ID=count;
    count++;
  }
  public String getName() {
    return name;
  }

  public int getPercent() {
    return percent;
  }

  public String getInfo() {
    return ("ID: " + ID + " Name: " + name + " Probability: " + percent);
  }


  @Override
  public int compareTo(Toy other) {
    if (other == null) {
      return -1;
    }
    int delta = this.percent - other.percent;
    if (delta != 0) {
      return -delta;
    }
    return this.name.compareTo(other.name);
  }
}
