import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner sc;
    public List<WiseSaying> list = new ArrayList<>();
    String menu;

    Controller() {
        sc = new Scanner(System.in);
    }

    void run() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            menu = sc.nextLine();

            switch (menuList(menu)) {
                case 0:
                    return;
                case 1:
                    addList();
            }
        }
    }

    public int menuList(String menu) {
        int menuNum = 0;
        if (menu.equals("종료")) menuNum = 0;
        else if (menu.equals("등록")) menuNum = 1;

        return menuNum;
    }

    void addList() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        list.add(new WiseSaying(content, author));
    }
}
