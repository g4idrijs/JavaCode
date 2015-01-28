public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
    System.out.println("\n**********");
    Bread bread1 = new Bread();
    Bread bread2 = new Bread();

    new Meal();
  }
}


class Bread {
  static{
    System.out.println("Bread()中static成员先初始化。");
  }
  public Bread() {
    System.out.println("bread");
  }
}

class Meal {
  
  public Meal() {
    System.out.println("meal");
  }
  
  Bread bread = new Bread();
}