import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DaySeven {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);


        partOne(lines);
        partTwo(lines);
    }

    //Not great, shouldn't have leveraged bitmasking for part 1
    public static void partTwo(List<String> lines) throws IOException {
        long result = 0;
        for(String line: lines) {
            List<String> items = new ArrayList<>();
            items.addAll( Arrays.asList(line.split(" ")));
            String first = items.getFirst();
            first = first.substring(0, first.length() - 1);
            long testValue = Long.parseLong(first);
            items.removeFirst();

            List<List<String>> formulas = new ArrayList<>();
            for(int k = 0; k < (1 << items.size()-1); ++k) {
                for (int i = 0; i < (1 << items.size() - 1); ++i) {
                    List<String> operators = new ArrayList<>();
                    operators.add(items.getFirst());
                    for (int j = 0; j < items.size() - 1; ++j) {
                        int current = Integer.parseInt(items.get(j + 1));
                        if ((i & (1 << j)) > 0) {
                            operators.add("c");
                        } else if((k & (1 << j)) > 0){
                            operators.add("*");
                        } else {
                            operators.add("+");
                        }
                        operators.add(String.valueOf(current));
                    }
                    formulas.add(operators);
                }
            }
            //System.out.println("Done");
            //System.out.println(formulas);

            //now check each formula
            for(int i = 0; i < formulas.size(); ++i) {
                long res = Long.parseLong(formulas.get(i).getFirst());
                for(int j = 1; j < formulas.get(i).size()-1; ++j) {
                    String currentStr = formulas.get(i).get(j);
                    String nextStr = formulas.get(i).get(j+1);
                    if(currentStr.equals("*")){
                        res *= Long.parseLong(nextStr);
                    } else if(currentStr.equals("+")) {
                        res += Long.parseLong(nextStr);
                    } else if(currentStr.equals("c")) {
                        res = Long.parseLong(String.valueOf(res) + nextStr);
                    }
                }
                if(res == testValue) {
                    result += testValue;
                    break;
                }
            }
        }
        System.out.println(result);
    }

    public static void partOne(List<String> lines) throws IOException{
        long result = 0;
        for(String line: lines) {
            List<String> items = new ArrayList<>();
            items.addAll( Arrays.asList(line.split(" ")));
            String first = items.getFirst();
            first = first.substring(0, first.length() - 1);
            long testValue = Long.parseLong(first);
            items.removeFirst();

            // 0 is +,  1 is *
            for(int i = 0; i < (1 << items.size()-1); ++i) {
                long res = Integer.parseInt(items.getFirst());
                for(int j = 0; j < items.size()-1; ++j) {
                    int current = Integer.parseInt(items.get(j+1));
                    if((i & (1<<j)) > 0){
                        res *= current;
                    } else {
                        res += current;
                    }
                }
                if(res == testValue) {
                    result += testValue;
                    break;
                }
            }
            System.out.println("Done");
        }
        System.out.println(result);
    }
}