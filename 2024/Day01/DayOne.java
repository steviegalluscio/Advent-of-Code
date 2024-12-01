import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DayOne {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");

        // Read input into String
        /* 
        String input = Files.readString(filePath);
        System.out.println(input);
        */

        // Split csv into List
        /*
        List<String> items = Arrays.asList(input.split(","));
        for (String item : items) {
            System.out.println(item);
        }
        */

        // Read input line into List of Strings
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            System.out.println(line);
        }
        
    }
}
