import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayEleven {

    public static void main(String [] args) throws IOException{

        //Path filePath = Paths.get("input.txt");

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
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);



        partOne(lines);
        //partTwo(lines);
    }

    public static void partOne(List<String> lines) {
        int result = 0;
        for(int i = 0; i < lines.size(); i ++){
            for(int j = 0; j < lines.getFirst().length(); j++){

            }
        }
        System.out.println(result);
    }

}