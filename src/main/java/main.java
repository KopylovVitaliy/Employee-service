import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) throws IOException {
        String file =  "Report.json";
        System.out.println(readTextFromFile(file));
    }
    private static String readTextFromFile(String fileName) throws IOException {
        try {
            return Files.lines(Paths.get(fileName), Charset.forName("windows-1251"))
                    .collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }
}
