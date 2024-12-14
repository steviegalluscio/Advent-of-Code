import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayEleven {

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> stonesImmut = Arrays.asList(Files.readString(filePath).split(" "));
        List<String> stones = new ArrayList<>();
        stones.addAll(stonesImmut);

        partOne(stones, 25);
        partTwo(stonesImmut, 75);
    }

    public static Map<String, Long> subRoutineCache = new HashMap<>(); //Maps stone+blink to result

    public static void partTwo(List<String> stones, int blinks) {
        long result = 0;
        for(String stone : stones){
            result += runner(stone, blinks);
        }
        System.out.println(result);
    }

    public static long runner(String stone, int blinks) {
        Long result;
        if((result = subRoutineCache.getOrDefault(stone+"+"+blinks, null)) != null){
            return result;
        }

        if(blinks == 0){
            return 1;
        }
        if (stone.equals("0")) {
            long save = runner("1", blinks-1);
            subRoutineCache.put(stone+"+"+blinks, save);
            return save;
        } else if (stone.length() % 2 == 0) {
            int mid = stone.length() / 2;
            String left = stone.substring(0, mid);
            String right = stone.substring(mid);
            while (right.charAt(0) == '0' && right.length() > 1) {
                right = right.replaceFirst("0", "");
            }
            long save = runner(left, blinks-1) + runner(right, blinks-1);
            subRoutineCache.put(stone+"+"+blinks, save);
            return save;
        } else {
            String newPetRock = String.valueOf(Long.parseLong(stone) * 2024);
            long save = runner(newPetRock, blinks-1);
            subRoutineCache.put(stone+"+"+blinks, save);
            return save;
        }
    }


    public static void partOne(List<String> stones, int blinks) {
        //System.out.println(stones);
        while (blinks-- > 0) {
            for (int i = 0; i < stones.size(); i++) {
                String engraved = stones.get(i);
                if (engraved.equals("0")) {  //If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
                    stones.set(i, "1");
                } else if (engraved.length() % 2 == 0) { //If the stone is engraved with a number that has an even number of digits,
                    //it is replaced by two stones.
                    //The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone.
                    int mid = engraved.length() / 2;
                    String left = engraved.substring(0, mid);
                    String right = engraved.substring(mid);
                    while (right.charAt(0) == '0' && right.length() > 1) { // (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
                        right = right.replaceFirst("0", "");
                    }
                    stones.set(i, right);
                    stones.add(i, left);
                    i++; // Skip new stone for this blink
                } else { //If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.
                    stones.set(i, String.valueOf(Long.parseLong(engraved) * 2024));
                }
                //System.out.println(stones);
            }
        }
        System.out.println(stones.size());
    }

}