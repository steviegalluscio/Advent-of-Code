import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayThirteen {

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        partOne(lines, 0);
        partOne(lines, 10000000000000L);
    }

    public static class ClawMachine {
        public int aX;
        public int aY;
        public int bX;
        public int bY;
        public long prizeX;
        public long prizeY;
    }

    public static void partOne(List<String> lines, long shift) {
        List<ClawMachine> clawMachines =  new ArrayList<>();
        ClawMachine clawMachine = new ClawMachine();
        for(String line : lines) {
            if(line.startsWith("Button")){
                int sep = line.indexOf(",");
                int x = Integer.parseInt(line.substring(11, sep));
                int y = Integer.parseInt(line.substring(sep+3));
                if(line.charAt(7) == 'A') {
                    clawMachine.aX = x;
                    clawMachine.aY = y;
                } else {
                    clawMachine.bX = x;
                    clawMachine.bY = y;
                }
            } else if (line.startsWith("Prize: X=")) {
                int sep = line.indexOf(",");
                int x = Integer.parseInt(line.substring(9, sep));
                int y = Integer.parseInt(line.substring(sep+4));
                clawMachine.prizeX = x + shift;
                clawMachine.prizeY = y + shift;
                clawMachines.add(clawMachine);
                clawMachine = new ClawMachine(); //reset
            }
        }

        long tokens = 0;
        for(ClawMachine cm : clawMachines){
            tokens += play(cm);
        }
        System.out.println(tokens);
    }

    public static long play(ClawMachine cm){
        double detA = cm.aX*cm.bY - cm.aY*cm.bX;
        double a = cm.bY/detA;
        double b = -cm.bX/detA;
        double c = -cm.aY/detA;
        double d = cm.aX/detA;
        double pressedA = (a * cm.prizeX) + (b * cm.prizeY);
        double pressedB = (c * cm.prizeX) + (d * cm.prizeY);
        long strippedA = Math.round(pressedA);
        long strippedB = Math.round(pressedB);
        if(Math.abs(pressedA - strippedA) < 0.001 && Math.abs(pressedB - strippedB) < 0.001) {
            return ((strippedA * 3) + (strippedB));
        }
        return 0;
    }

}