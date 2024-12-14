import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayTen {
    public static int [][] trailMap;

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines);
        partTwo(lines);
    }

    public static void partTwo(List<String> lines) {
        int result = 0;
        Set<String> trailHeads = new HashSet<>();
        trailMap = new int[lines.size()][lines.getFirst().length()];
        for(int i = 0; i < lines.size(); i ++){
            for(int j = 0; j < lines.getFirst().length(); j++){
                trailMap[i][j] = lines.get(i).charAt(j) - '0';
                if(trailMap[i][j] == 0){
                    trailHeads.add(i+";"+j);
                }
            }
        }
        for(String trailHead : trailHeads){
            String [] items = trailHead.split(";");
            int i = Integer.parseInt(items[0]);
            int j = Integer.parseInt(items[1]);
            result += run2(i , j , 0,new ArrayList<>());
        }
        System.out.println(result);
    }

    public static int run2(int i, int j, int prev, List<String> trailEnds){
        //check up down left right
        int up = i-1 >= 0 ? trailMap[i-1][j] : -1;
        int down = i+1 < trailMap.length ? trailMap[i+1][j] : -1;
        int left = j-1 >= 0 ? trailMap[i][j-1] : -1;
        int right = j+1 < trailMap[0].length ? trailMap[i][j+1] : -1;
        if(up >= 0 && up == prev+1){
            run2(i-1, j, up, trailEnds);
        }
        if(down >= 0 && down == prev+1){
            run2(i+1, j, down, trailEnds);
        }
        if(left >= 0 && left == prev+1){
            run2(i, j-1, left, trailEnds);
        }
        if(right >= 0 && right == prev+1){
            run2(i, j+1, right, trailEnds);
        }
        if(prev == 9){
            trailEnds.add(i+";"+j);
        }
        return trailEnds.size();
    }

    public static void partOne(List<String> lines) {
        int result = 0;
        Set<String> trailHeads = new HashSet<>();
        trailMap = new int[lines.size()][lines.getFirst().length()];
        for(int i = 0; i < lines.size(); i ++){
            for(int j = 0; j < lines.getFirst().length(); j++){
                trailMap[i][j] = lines.get(i).charAt(j) - '0';
                if(trailMap[i][j] == 0){
                    trailHeads.add(i+";"+j);
                }
            }
        }
        for(String trailHead : trailHeads){
            String [] items = trailHead.split(";");
            int i = Integer.parseInt(items[0]);
            int j = Integer.parseInt(items[1]);
            result += run(i , j , 0, new HashSet<>());
        }
        System.out.println(result);
    }

    public static int run(int i, int j, int prev, Set<String> trailEnds){
        //check up down left right
        int up = i-1 >= 0 ? trailMap[i-1][j] : -1;
        int down = i+1 < trailMap.length ? trailMap[i+1][j] : -1;
        int left = j-1 >= 0 ? trailMap[i][j-1] : -1;
        int right = j+1 < trailMap[0].length ? trailMap[i][j+1] : -1;
        if(up >= 0 && up == prev+1){
           run(i-1, j, up, trailEnds);
        }
        if(down >= 0 && down == prev+1){
            run(i+1, j, down, trailEnds);
        }
        if(left >= 0 && left == prev+1){
            run(i, j-1, left, trailEnds);
        }
        if(right >= 0 && right == prev+1){
            run(i, j+1, right, trailEnds);
        }
        if(prev == 9){
            trailEnds.add(i+";"+j);
        }
        return trailEnds.size();
    }
}