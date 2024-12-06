import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayFive {
    private static HashMap<String, Set<String>> rules = new HashMap<String, Set<String>>();
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        

        partOne(lines);
        partTwo(lines);
    }

    public static void partOne(List<String> lines) throws IOException{
        int result = 0;

        boolean firstHalf = true;
        for(String line: lines){ 
            if(firstHalf){
                String [] items = line.split("\\|");
                if(items.length > 1){
                    String key = items[0];
                    String value =  items[1];
                    if(rules.containsKey(key)){
                        rules.get(key).add(value);
                    } else {
                        Set<String> set = new HashSet<String>();
                        set.add(value);
                        rules.put(key, set);
                    }
                } else {
                    firstHalf = false;
                }
            } else {
               List<String> itemsOg = Arrays.asList(line.split(","));
               List<String> items = new ArrayList<>();
               items.addAll(itemsOg);
               items.sort((a,b) -> compare(a,b));
               if(items.equals(itemsOg)){
                    result += Integer.valueOf(items.get(items.size()/2));
               }
            }       
        }
        System.out.println(result);
    }

    public static void partTwo(List<String> lines) throws IOException{
        int result = 0;

        boolean firstHalf = true;
        for(String line: lines){ 
            if(firstHalf){
                String [] items = line.split("\\|");
                if(items.length > 1){
                    String key = items[0];
                    String value =  items[1];
                    if(rules.containsKey(key)){
                        rules.get(key).add(value);
                    } else {
                        Set<String> set = new HashSet<String>();
                        set.add(value);
                        rules.put(key, set);
                    }
                } else {
                    firstHalf = false;
                }
            } else {
               List<String> itemsOg = Arrays.asList(line.split(","));
               List<String> items = new ArrayList<>();
               items.addAll(itemsOg);
               items.sort((a,b) -> compare(a,b));
               if(!items.equals(itemsOg)){
                    result += Integer.valueOf(items.get(items.size()/2));
               }
            }       
        }
        System.out.println(result);
    }

    public static int compare(String a, String b){
        //-1 if a<b
        //+1 if a>b
        if(rules.containsKey(a) && rules.get(a).contains(b)){
            return -1;
        }
        return 1;
    }
}

