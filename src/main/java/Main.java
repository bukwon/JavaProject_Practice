import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            String menu = sc.nextLine();
            if(menu.equals("종료")) return;
        }
    }
}
