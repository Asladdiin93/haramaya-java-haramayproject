import java.util.Scanner;
 
public class Hello {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter your name: ");
      String name = sc.nextLine();

      System.out.println("\n🥰 Hello" + name + "!");
        System.out.println("Hello from Haramaya University! 🇪🇹");
        System.out.println("IT Department — Coding on Android! 💪");

        sc.close();
    }
}
