import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner sc;
    public List<WiseSaying> list;
    String menu;
    int id = 0;

    Controller() {
        sc = new Scanner(System.in);
        list = new ArrayList<>();
        loadFromTextFile();
    }

    void run() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            menu = sc.nextLine();

            switch (menuList(menu)) {
                case 0:
                    saveToTextFile();
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
        System.out.println(++id + "번 명언이 등록되었습니다.");
        list.add(new WiseSaying(id, content, author));
    }

    private void saveToTextFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("wise_sayings.txt"))) {
            for (WiseSaying saying : list) {
                writer.println(saying.getId() + "," + saying.getContent() + "," + saying.getAuthor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("wise_sayings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String content = parts[1];
                String author = parts[2];
                list.add(new WiseSaying(id, content, author));
                this.id = id; // 마지막 id 업데이트, id를 통해 영구적 저장
            }
        } catch (IOException e) {
            // 파일이 없을 수 있으므로 무시
        } catch (NumberFormatException e) {
            System.err.println("잘못된 형식의 파일입니다.");
        }
    }
}
