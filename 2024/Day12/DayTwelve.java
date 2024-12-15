import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayTwelve {
    public static char [][] farm;

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input lines as List of Strings
        List<String> lines = Files.readAllLines(filePath);

        farm = new char[lines.size()][lines.getFirst().length()];
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.getFirst().length(); j++) {
                farm[i][j] = lines.get(i).charAt(j);
                //System.out.print(farm[i][j]);
            }
            //System.out.println();
        }

        //partOne(lines);
        partTwo(lines);
    }

    public static class Pair {
        public int i;
        public int j;

        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }

        Character getPlant(){
            return farm[this.i][this.j];
        }
    }

    public static void partTwo(List<String> lines) {
        int totalPrice = 0;
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.getFirst().length(); j++){
                Character c = farm[i][j];
                if(c.equals(Character.toUpperCase(c))) {
                    totalPrice += flood2(i, j);
                }
            }
        }
        System.out.println(totalPrice);
    }

    public static int flood2(int i, int j) {
        Set<Pair> plants = new HashSet<>();
        int corners = 0;
        int area = 0;
        Queue<Pair> q = new LinkedList<>();
        Character plantType = farm[i][j];
        q.add(new Pair(i,j));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            Character c = p.getPlant();
            if(c.equals(plantType)){
                farm[p.i][p.j] = Character.toLowerCase(c);
                area++;
                plants.add(new Pair(p.i, p.j));
                if(p.i-1 >= 0){ //north
                    q.add(new Pair(p.i-1, p.j));
                }
                if(p.i+1 < farm.length){
                    q.add(new Pair(p.i+1, p.j));
                }
                if(p.j-1 >= 0){
                    q.add(new Pair(p.i, p.j-1));
                }
                if(p.j+1 < farm[0].length){
                    q.add(new Pair(p.i, p.j+1));
                }
            }
        }

        // Find corners==sides after because it's easier to add on
        for(Pair p : plants){
            Character center = farm[p.i][p.j];

            Character north = p.i-1 >= 0 ? farm[p.i-1][p.j] : '#';
            Character east = p.j+1 < farm[0].length? farm[p.i][p.j+1] : '#';
            Character northEast = north != '#' && east != '#' ? farm[p.i-1][p.j+1] : '#';

            //NE
            if(center.equals(east) && north.equals(east) && !northEast.equals(east)){
                corners++;
            } else if(!center.equals(east) && !center.equals(north)){
                corners++;
            }

            //SW
            Character south = p.i+1 < farm.length ? farm[p.i+1][p.j] : '#';
            Character west = p.j-1 >= 0? farm[p.i][p.j-1] : '#';
            Character southWest = south != '#' && west != '#' ? farm[p.i+1][p.j-1] : '#';

            if(center.equals(west) && south.equals(west) && !southWest.equals(west)){
                corners++;
            } else if(!center.equals(west) && !center.equals(south)){
                corners++;
            }

            Character northWest = north != '#' && west != '#' ? farm[p.i-1][p.j-1] : '#';

            //NW
            if(center.equals(west) && north.equals(west) && !northWest.equals(west)){
                corners++;
            } else if(!center.equals(west) && !center.equals(north)){
                corners++;
            }

            Character southEast = south != '#' && east != '#' ? farm[p.i+1][p.j+1] : '#';

            //SE
            if(center.equals(east) && south.equals(east) && !southEast.equals(east)){
                corners++;
            } else if(!center.equals(east) && !center.equals(south)){
                corners++;
            }
        }

        return area * corners;
    }

    public static void partOne(List<String> lines) {
        // area and perimeter, consider # of plants to be area
        // use flood fill to find regions
        // to find perimiter keep track of how many non-alike plants found in food fill
        int totalPrice = 0;
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.getFirst().length(); j++){
                Character c = farm[i][j];
                if(c.equals(Character.toUpperCase(c))) {
                    totalPrice += flood(i, j);
                }
            }
        }
        System.out.println(totalPrice);
    }

    public static int flood(int i, int j) {
        int area = 0;
        int perimiter = 0;
        Queue<Pair> q = new LinkedList<>();
        Character plantType = farm[i][j];
        q.add(new Pair(i,j));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            Character c = p.getPlant();
            if(c.equals(plantType)){
                farm[p.i][p.j] = Character.toLowerCase(c);
                area++;
                if(p.i-1 >= 0){
                    q.add(new Pair(p.i-1, p.j));
                } else {
                    perimiter++; //outer
                }
                if(p.i+1 < farm.length){
                    q.add(new Pair(p.i+1, p.j));
                } else {
                    perimiter++; //outer
                }
                if(p.j-1 >= 0){
                    q.add(new Pair(p.i, p.j-1));
                } else {
                    perimiter++; //outer
                }
                if(p.j+1 < farm[0].length){
                    q.add(new Pair(p.i, p.j+1));
                } else {
                    perimiter++; //outer
                }
            } else {
                Character lowerPlantType = Character.toLowerCase(plantType);
                if(!c.equals(lowerPlantType)){
                    perimiter++; //inner
                }
            }
        }
        //System.out.println("area" + area);
        //System.out.println("perimiter" + perimiter);
        return area * perimiter;
    }
}