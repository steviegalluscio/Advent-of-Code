import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DayFour {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines);
        partTwo(lines);
    }

    public static void partOne(List<String> lines) throws IOException{
        int result = 0;
        int rowsCount = lines.size();
        int colCount = lines.get(0).length();

        for(int i = 0; i < rowsCount; i++){
            for(int j = 0; j < colCount; j++){
                String up = "";
                String down = "";
                String left = "";
                String right = "";
                String upLeft = "";
                String upRight = "";
                String downLeft = "";
                String downRight = "";

                int k = 0;
                int l = 0;
                boolean going = true;
                boolean bup = true;
                boolean bdown = true;
                boolean bleft = true;
                boolean bright = true;
                while(going && k < 4 && l < 4){
                    going = false;
                    bup = false;
                    bdown = false;
                    bleft = false;
                    bright = false;
                    if(i-k >= 0){
                        up += lines.get(i-k).charAt(j);
                        going = true;
                        bup = true;
                    }
                    if(i+k < rowsCount){
                        down += lines.get(i+k).charAt(j);
                        going = true;
                        bdown = true;
                    }
                    if(j-l >= 0){
                        left += lines.get(i).charAt(j-l);
                        going = true;
                        bleft = true;
                    }
                    if(j+l < colCount){
                        right += lines.get(i).charAt(j+l);
                        going = true;
                        bright = true;
                    }
                    if(bup && bleft){
                        upLeft += lines.get(i-k).charAt(j-l);
                    }
                    if(bup && bright){
                        upRight += lines.get(i-k).charAt(j+l);
                    }
                    if(bdown && bleft){
                        downLeft += lines.get(i+k).charAt(j-l);
                    }
                    if(bdown && bright){
                        downRight += lines.get(i+k).charAt(j+l);
                    }
                    k++;
                    l++;
                }


                if(up.equals("XMAS")){
                    //System.out.println("up at"+i+","+j);
                    result++;
                } 
                if(down.equals("XMAS") ){
                    //System.out.println("down at"+i+","+j);
                    result++;
                } 
                if(left.equals("XMAS") ){
                    //System.out.println("left at"+i+","+j);
                    result++;
                } 
                if(right.equals("XMAS")){
                    //System.out.println("right at"+i+","+j);
                    result++;
                } 
                if(upLeft.equals("XMAS")){
                    //System.out.println("upLeft at"+i+","+j);
                    result++;
                } 
                if(upRight.equals("XMAS")){
                    //System.out.println("upRight at"+i+","+j);
                    result++;
                } 
                if(downLeft.equals("XMAS")){
                    //System.out.println("downLeft at"+i+","+j);
                    result++;
                } 
                if(downRight.equals("XMAS")){
                    //System.out.println("downRight at"+i+","+j);
                    result++;
                } 
            }          
        }
        System.out.println(result);
    }

    public static void partTwo(List<String> lines) throws IOException{
        int result = 0;
        int rowsCount = lines.size();
        int colCount = lines.get(0).length();

        for(int i = 0; i < rowsCount; i++){
            for(int j = 0; j < colCount; j++){
                char mid = lines.get(i).charAt(j);
                if(mid != 'A'){continue;}
                String upLeft = "";
                String upRight = "";
                String downLeft = "";
                String downRight = "";

                int k = 1;
                int l = 1;
                boolean going = true;
                boolean bup = true;
                boolean bdown = true;
                boolean bleft = true;
                boolean bright = true;
                while(going && k < 2 && l < 2){
                    going = false;
                    bup = false;
                    bdown = false;
                    bleft = false;
                    bright = false;
                    if(i-k >= 0){
                        going = true;
                        bup = true;
                    }
                    if(i+k < rowsCount){
                        going = true;
                        bdown = true;
                    }
                    if(j-l >= 0){
                        going = true;
                        bleft = true;
                    }
                    if(j+l < colCount){
                        going = true;
                        bright = true;
                    }
                    if(bup && bleft){
                        upLeft += lines.get(i-k).charAt(j-l);
                    }
                    if(bup && bright){
                        upRight += lines.get(i-k).charAt(j+l);
                    }
                    if(bdown && bleft){
                        downLeft += lines.get(i+k).charAt(j-l);
                    }
                    if(bdown && bright){
                        downRight += lines.get(i+k).charAt(j+l);
                    }
                    k++;
                    l++;
                }

                int crossFound = 0;
                if((upLeft.equals("M") && downRight.equals("S") ) || (upLeft.equals("S") && downRight.equals("M") )){
                    crossFound++;
                } 
                if((upRight.equals("M") && downLeft.equals("S") ) || (upRight.equals("S") && downLeft.equals("M") )){
                    crossFound++;
                } 

                if(crossFound == 2){
                    result++;
                }               
            }
            
        }
        System.out.println(result);
    }
}

