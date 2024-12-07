import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySix {
    private static int rowsCount;
    private static int colCount;
    private static int startGuardX;
    private static int startGuardY;
    private static Set<String> blocker = new HashSet<String>();
    private static Set<String> visited = new HashSet<String>();
    private static List<String> lines =  new ArrayList<>();
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input lines as List of Strings
        lines = Files.readAllLines(filePath);
        rowsCount = lines.size();
        colCount = lines.get(0).length();

        partOne();
        partTwo();
    }

    public static void partTwo() throws IOException{
        int result = 0;

        for(String loc: visited){
            String [] items = loc.split(";");
            Integer blockX = Integer.valueOf(items[0]);
            Integer blockY = Integer.valueOf(items[1]);
            if(!(startGuardX == blockX && blockY == startGuardY) && hasCycle(startGuardX, startGuardY, blockX, blockY)){
                result++;
            }
        }

        System.out.println(result); 
    }


    public static boolean hasCycle(Integer guardX, Integer guardY, Integer blockX, Integer blockY) {
        //Add new blocker
        String block = blockX.toString()+";"+blockY.toString();
        blocker.add(block);

        int fastUp = 1;
        int fastRight = 0;
        Integer fastGuardX = guardX;
        Integer fastGuardY = guardY;
        int slowUp = 1;
        int slowRight = 0;
        Integer slowGuardX = guardX;
        Integer slowGuardY = guardY;
        int alternator = 1;

        while(fastGuardX >= 0 && fastGuardY >= 0 && fastGuardX <= colCount && fastGuardY <= rowsCount){ //TODO: think if this should be <=
            alternator *= -1;

            Integer fastTmpGuardY = fastGuardY - fastUp;
            Integer fastTmpGuardX = fastGuardX + fastRight;
            String nextFast = fastTmpGuardX.toString()+";"+fastTmpGuardY.toString();
            if(blocker.contains(nextFast)){
                //If there is something directly in front of you, turn right 90 degrees.
                if(fastUp == 1 && fastRight == 0){
                    fastUp = 0;
                    fastRight = 1;
                } else if(fastUp == 0 && fastRight == 1){
                    fastUp = -1;
                    fastRight = 0;
                } else if(fastUp == -1 && fastRight == 0){
                    fastUp = 0;
                    fastRight = -1;
                } else if(fastUp == 0 && fastRight == -1){
                    fastUp = 1;
                    fastRight = 0;
                }
            } else {
                //Otherwise, take a step forward.
                fastGuardY = fastTmpGuardY;
                fastGuardX = fastTmpGuardX;                
            }

            if(alternator == 1){
                Integer slowTmpGuardY = slowGuardY - slowUp;
                Integer slowTmpGuardX = slowGuardX + slowRight;
                String nextSlow = slowTmpGuardX.toString()+";"+slowTmpGuardY.toString();
                if(blocker.contains(nextSlow)){
                    //If there is something directly in front of you, turn right 90 degrees.
                    if(slowUp == 1 && slowRight == 0){
                        slowUp = 0;
                        slowRight = 1;
                    } else if(slowUp == 0 && slowRight == 1){
                        slowUp = -1;
                        slowRight = 0;
                    } else if(slowUp == -1 && slowRight == 0){
                        slowUp = 0;
                        slowRight = -1;
                    } else if(slowUp == 0 && slowRight == -1){
                        slowUp = 1;
                        slowRight = 0;
                    }
                } else {
                    //Otherwise, take a step forward.
                    slowGuardY = slowTmpGuardY;
                    slowGuardX = slowTmpGuardX;                
                }
            }

            if(fastGuardX == slowGuardX && fastGuardY == slowGuardY && fastUp == slowUp && fastRight == slowRight){
                blocker.remove(block);
                return true;
            }

        }
        blocker.remove(block);
        return false;
    }

    public static void partOne() throws IOException{
        //Find guard and blockers
        char [][] map = new char[rowsCount][colCount];
        Integer guardX = 0;
        Integer guardY = 0;
        for(Integer y = 0; y < rowsCount; y++){
            for(Integer x = 0; x < colCount; x++){
                if(lines.get(y).charAt(x) == '#'){
                    blocker.add(x.toString()+";"+y.toString());
                } else if (lines.get(y).charAt(x) == '^'){
                    guardX = x;
                    guardY = y;
                    startGuardX = x;
                    startGuardY = y;
                }
            }
        }
 

        int up = 1;
        int right = 0;
        while(guardX >= 0 && guardY >= 0 && guardX <= colCount && guardY <= rowsCount){
            String cur = guardX.toString()+";"+guardY.toString();
            Integer tmpGuardY = guardY - up;
            Integer tmpGuardX = guardX + right;
            String next = tmpGuardX.toString()+";"+tmpGuardY.toString();
            if(blocker.contains(next)){
                //If there is something directly in front of you, turn right 90 degrees.
                if(up == 1 && right == 0){
                    up = 0;
                    right = 1;
                } else if(up == 0 && right == 1){
                    up = -1;
                    right = 0;
                } else if(up == -1 && right == 0){
                    up = 0;
                    right = -1;
                } else if(up == 0 && right == -1){
                    up = 1;
                    right = 0;
                }
            } else {
                //Otherwise, take a step forward.
                guardY = tmpGuardY;
                guardX = tmpGuardX;
                //Mark visited
                if(!visited.contains(cur)){
                    visited.add(cur);
                }          
            }
        }

        System.out.println(visited.size()); 
    }
}

