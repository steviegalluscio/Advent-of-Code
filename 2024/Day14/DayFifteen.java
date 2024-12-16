import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayFifteen {

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines);
        //partTwo(lines);
    }

    public static void partOne(List<String> lines) {

        for(String line : lines) {

        }

        System.out.println();
    }
}