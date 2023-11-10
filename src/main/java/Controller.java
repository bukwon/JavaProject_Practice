import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private Scanner sc;
    public List<WiseSaying> list;
    private String menu;
    public int id = 0;
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private FileController file = new FileController();
    private JsonFileController json = new JsonFileController();

    Controller() {
        sc = new Scanner(System.in);
        list = file.loadFromTextFile();
        if (!list.isEmpty()) {
            id = list.get(list.size() - 1).getId();
        }
        json.loadFromJsonFile(); // JSON 파일에서 데이터 로드
    }

    void run() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령) ");
            menu = sc.nextLine();

            switch (menuList(menu)) {
                case 0:
                    file.saveToTextFile(list);
                    json.saveToJsonFile(list);
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

        file.saveToTextFile(list);
        json.saveToJsonFile(list);      // 명언을 파일과 json에 저장
    }
}

