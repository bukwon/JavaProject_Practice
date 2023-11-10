import java.util.List;
import java.util.Scanner;

public class Controller {
    private Scanner sc;
    public List<WiseSaying> list;
    private String menu;
    public int id = 0;
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
                    break;
                case 2:
                    checkList();
                    break;
            }
        }
    }

    public int menuList(String menu) {
        int menuNum = 0;
        if (menu.equals("종료")) menuNum = 0;
        else if (menu.equals("등록")) menuNum = 1;
        else if (menu.equals("목록")) menuNum = 2;

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

    void checkList() {
        System.out.println("번호 \t / \t 작가 \t / \t 명언");
        int printLength = json.loadFromJsonFile().size();
        try{
//            System.out.println(objectMapper.readValue(new File("data.json"), new TypeReference<List<WiseSaying>>() {}));
            for (int i = 0; i < printLength; i++) {
                System.out.println(json.loadFromJsonFile().get(i).id + "\t\t" +
                        json.loadFromJsonFile().get(i).author + "\t" +
                        json.loadFromJsonFile().get(i).content);
            }
        } catch (Exception e) {
            System.out.println("목록 출력 에러!");
        }
    }
}