import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DayTwo {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input lines as List of Strings
        List<String> reports = Files.readAllLines(filePath);

        partOne(reports);
        partTwo(reports);
    }


    public static void partOne(List<String> reports) throws IOException{
        int safeCount = 0;
        for(String report: reports){
            String [] levels = report.split(" ");
            if(checkReport(levels, -1)){ safeCount++; }
        }
        System.out.println(safeCount);
    }


    // Bruteforce by trying to skip each level until report is safe
    public static void partTwo(List<String> reports) throws IOException{
        int safeCount = 0;
        for(String report: reports){
            String [] levels = report.split(" ");
            for(int i = -1; i < levels.length; i++){
                if(checkReport(levels, i)){
                    safeCount++;
                    break;
                }
            }
        }
        System.out.println(safeCount);
    }

    public static boolean checkReport(String [] levels, int skip){
        boolean safe = true;
        boolean inc = true;
        boolean dec = true;
        for(int i = 0; i < levels.length - 1; i++){
            int p1 = i;
            int p2 = i+1;
            if(p1 == skip){
                continue;
            } else if (p2 == skip){
                if(p2 >= levels.length - 1){ break; }
                p2++;
            }
            int curLevel = Integer.valueOf(levels[p1]);
            int nextLevel = Integer.valueOf(levels[p2]);
            int dist = Math.abs(curLevel - nextLevel);
            if(!(dist > 0 && dist < 4)){
                safe = false;
                break;
            }
            if(curLevel < nextLevel){
                dec = false;
            }
            if(nextLevel < curLevel){
                inc = false;
            }
        }
        if(safe && ((dec && !inc) || (!dec && inc)) ){ return true; }
        return false;
    }
}
