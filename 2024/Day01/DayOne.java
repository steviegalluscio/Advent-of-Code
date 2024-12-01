import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DayOne {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines);
        partTwo(lines);
    }

    // Worst case time complexity: O(n*log(n))
    // Because uses min heap to sort with O(log(n)) enqueing and dequeing
    public static void partOne(List<String> lines) throws IOException{
        PriorityQueue<Integer> minHeapLeft = new PriorityQueue<Integer>();
        PriorityQueue<Integer> minHeapRight = new PriorityQueue<Integer>();
        for(String line: lines){
            String [] listItems = line.split("   ");
            int numberLeft = Integer.valueOf(listItems[0]);
            int numberRight = Integer.valueOf(listItems[1]);
            minHeapLeft.add(numberLeft);
            minHeapRight.add(numberRight);
        }
        long totalDistance = 0;
        for(int i = 0; i < lines.size(); i++){
            int d = Math.abs(minHeapLeft.poll() - minHeapRight.poll());
            totalDistance += d;
        }
        System.out.println(totalDistance);
    }

    // Worst case time complexity: O(n*log(n))
    // Because Integer implements Comparable, map add/get is O(log(n))
    public static void partTwo(List<String> lines) throws IOException{
        List<Integer> listLeft = new ArrayList<>();
        Map<Integer, Integer> mapRight = new HashMap<>();
        // Populate map with <numberRight, numberRight's occurrences in right list>
        for (String line : lines) {
            String [] listItems = line.split("   ");
            int numberLeft = Integer.valueOf(listItems[0]);
            int numberRight = Integer.valueOf(listItems[1]);
            listLeft.add(numberLeft);
            mapRight.merge(numberRight, 1, Integer::sum); // Increment occurrences for number by 1
        }
        // Loop over left list and lookup occurrences in right
        long similarityScore = 0;
        for (Integer leftItem : listLeft) {
            similarityScore += leftItem * mapRight.getOrDefault(leftItem, 0);
        }
        System.out.println(similarityScore);
    }
}
