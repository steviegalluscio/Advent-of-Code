import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayEight {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines);
        partTwo(lines);
    }

    public static void partTwo(List<String> lines) {
        int result = 0;
        Map<Character, Set<String>> map = new HashMap<>();
        Location [][] locations = new Location[lines.size()][lines.getFirst().length()];
        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.getFirst().length(); x++) {
                Character c = lines.get(y).charAt(x);
                locations[y][x] = new Location();
                locations[y][x].hasAntinode = false;
                if(!c.equals('.')){
                    locations[y][x].antennas = new HashSet<>();
                    locations[y][x].antennas.add(c);
                    String value = x + "," + y;
                    if(map.containsKey(c)){
                        map.get(c).add(value);
                    } else {
                        Set<String> set = new HashSet<String>();
                        set.add(value);
                        map.put(c, set);
                    }
                }
                //System.out.print(c);
            }
            //System.out.println();
        }

        for(Character c: map.keySet()){
            //System.out.println(c);
            List<String> like  = map.get(c).stream().toList();
            for(int i = 0; i < like.size(); i++){
                String a = like.get(i);
                String [] bothA = a.split(",");
                int x1 = Integer.parseInt(bothA[0]);
                int y1 = Integer.parseInt(bothA[1]);
                for(int j = i+1; j < like.size(); j++){
                    String b = like.get(j);
                    //System.out.println(a + "and" + b);
                    String [] bothB = b.split(",");
                    int x2 = Integer.parseInt(bothB[0]);
                    int y2 = Integer.parseInt(bothB[1]);
                    //Find sink and run for b
                    int sink = y2 - y1;
                    int run = x2 - x1;
                    // Reduce frac
                    BigInteger bigSink = BigInteger.valueOf(sink);
                    BigInteger bigRun = BigInteger.valueOf(run);
                    int gcd = bigSink.gcd(bigRun).intValue();
                    sink = sink / gcd;
                    run = run / gcd;
                    //System.out.println("sink"+sink+",run"+run);
                    //Also go towards, not just away this time
                    int tempY2 = y2;
                    int tempX2 = x2;
                    while (tempY2+sink < locations.length && tempX2+run < locations[0].length && tempY2+sink >= 0 && tempX2+run >= 0) {
                        locations[tempY2 + sink][tempX2 + run].hasAntinode = true;
                        tempY2 = tempY2 + sink;
                        tempX2 = tempX2 + run;
                    }
                    tempY2 = y2;
                    tempX2 = x2;
                    while (tempY2-sink < locations.length && tempX2-run < locations[0].length && tempY2-sink >= 0 && tempX2-run >= 0) {
                        locations[tempY2 - sink][tempX2 - run].hasAntinode = true;
                        tempY2 = tempY2 - sink;
                        tempX2 = tempX2 - run;
                    }
                    //Note a is the same but inverse
                    int tempY1 = y1;
                    int tempX1 = x1;
                    while (tempY1-sink >= 0 && tempX1-run >= 0 && tempY1-sink < locations.length && tempX1-run < locations[0].length) {
                        locations[tempY1 - sink][tempX1 - run].hasAntinode = true;
                        tempY1 = tempY1 - sink;
                        tempX1 = tempX1 - run;
                    }
                    tempY1 = y1;
                    tempX1 = x1;
                    while (tempY1+sink >= 0 && tempX1+run >= 0 && tempY1+sink < locations.length && tempX1+run < locations[0].length) {
                        locations[tempY1 + sink][tempX1 + run].hasAntinode = true;
                        tempY1 = tempY1 + sink;
                        tempX1 = tempX1 + run;
                    }
                }
            }
        }

        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.getFirst().length(); x++) {
                Character c = lines.get(y).charAt(x);
                if(locations[y][x].hasAntinode){
                    //System.out.print('#');
                    result++;
                } else {
                    //System.out.print(c);
                }
            }
            //System.out.println();
        }
        System.out.println(result);
    }

    public static void partOne(List<String> lines) {
        int result = 0;
        Map<Character, Set<String>> map = new HashMap<>();
        Location [][] locations = new Location[lines.size()][lines.getFirst().length()];
        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.getFirst().length(); x++) {
                Character c = lines.get(y).charAt(x);
                locations[y][x] = new Location();
                locations[y][x].hasAntinode = false;
                if(!c.equals('.')){
                    locations[y][x].antennas = new HashSet<>();
                    locations[y][x].antennas.add(c);
                    String value = x + "," + y;
                    if(map.containsKey(c)){
                        map.get(c).add(value);
                    } else {
                        Set<String> set = new HashSet<String>();
                        set.add(value);
                        map.put(c, set);
                    }
                }
                //System.out.print(c);
            }
            //System.out.println();
        }

        for(Character c: map.keySet()){
            //System.out.println(c);
            List<String> like  = map.get(c).stream().toList();
            for(int i = 0; i < like.size(); i++){
                String a = like.get(i);
                String [] bothA = a.split(",");
                int x1 = Integer.parseInt(bothA[0]);
                int y1 = Integer.parseInt(bothA[1]);
                for(int j = i+1; j < like.size(); j++){
                    String b = like.get(j);
                    //System.out.println(a + "and" + b);
                    String [] bothB = b.split(",");
                    int x2 = Integer.parseInt(bothB[0]);
                    int y2 = Integer.parseInt(bothB[1]);
                    //Find sink and run for b
                    int sink = y2 - y1;
                    int run = x2 - x1;
                    //System.out.println("sink"+sink+",run"+run);
                    if(y2+sink < locations.length && x2+run < locations[0].length && y2+sink >= 0 && x2+run >= 0) {
                        locations[y2 + sink][x2 + run].hasAntinode = true;
                    }
                    //Note a is the same but inverse
                    if(y1-sink >= 0 && x1-run >= 0 && y1-sink < locations.length && x1-run < locations[0].length) {
                        locations[y1 - sink][x1 - run].hasAntinode = true;
                    }
                }
            }
        }

        for(int y = 0; y < lines.size(); y++) {
            for(int x = 0; x < lines.getFirst().length(); x++) {
                Character c = lines.get(y).charAt(x);
                if(locations[y][x].hasAntinode){
                   //System.out.print('#');
                   result++;
                } else {
                    //System.out.print(c);
                }
            }
            //System.out.println();
        }
        System.out.println(result);
    }
}