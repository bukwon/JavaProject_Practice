import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileController {
    FileController() {}

    void saveToTextFile(List<WiseSaying> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("wise_sayings.txt"))) {
            for (WiseSaying saying : list) {
                writer.println(saying.getId() + "," + saying.getContent() + "," + saying.getAuthor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<WiseSaying> loadFromTextFile() {
        List<WiseSaying> loadedList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("wise_sayings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idNum = Integer.parseInt(parts[0]);
                String content = parts[1];
                String author = parts[2];
                loadedList.add(new WiseSaying(idNum, content, author));
            }
        } catch (IOException | NumberFormatException e) {
            // 파일이 없을 수 있으므로 무시
            System.err.println("잘못된 형식의 파일입니다.");
        }
        return loadedList;
    }
}

class JsonFileController {
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    JsonFileController() {}

    void saveToJsonFile(List<WiseSaying> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.json"))) {
            String json = objectMapper.writeValueAsString(list);
            writer.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<WiseSaying> loadFromJsonFile() {
        List<WiseSaying> loadedList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            WiseSaying[] sayings = objectMapper.readValue(json.toString(), WiseSaying[].class);
            for (WiseSaying saying : sayings) {
                loadedList.add(saying);
            }
        } catch (IOException e) {
            // 파일이 없을 수 있으므로 무시
        }
        return loadedList;
    }
}