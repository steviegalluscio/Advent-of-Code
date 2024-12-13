import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayNine {
    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("./input.txt");
        // Read input line as String
        String line = Files.readString(filePath);

        partOne(line);
        partTwo(line);
    }

    public static void partTwo(String line) {
        List<String> fs = new ArrayList<>();
        List<Integer> files = new ArrayList<>();
        for(int i = 0; i < line.length(); i+=2){
            int file = line.charAt(i) - '0';
            int free = i+1 < line.length() ? line.charAt(i+1) - '0' : 0;;
            files.add(file);
            String fullFile = "";
            for(int j = 0; j < file; j++){
                fullFile += (i/2) + ";";

            }
            fs.add(fullFile);
            for(int j = 0; j < free; j++){
                fs.add(".");
            }
        }
        //System.out.println(fs);

        long checksum = 0;
        while(!files.isEmpty()){
            int fileWidth = files.getLast();
            files.removeLast();
            int consecFree = 0;
            for(int i = 0; i < fs.size(); i++){
                if(fs.get(i) == "."){
                    consecFree++;
                    if(consecFree == fileWidth){
                        //good
                        String moving = "";
                        int m = fileWidth;
                        while(m-- > 0){
                            moving += files.size() + ";";
                        }
                        int place = i-consecFree+1;
                        int popIndex = fs.lastIndexOf(moving);
                        if(place < popIndex) {
                            String pop = fs.get(popIndex);
                            fs.set(popIndex, ".");
                            for (int j = 1; j < consecFree; j++) {
                                fs.add(popIndex, ".");
                                fs.remove(place);
                            }
                            fs.set(place, pop);
                        }
                        break;
                    }
                } else {
                    consecFree = 0;
                }

            }
            //System.out.println(fs);
        }
        long fakeIndex = 0;
        for(int i = 0; i < fs.size(); i++){
            String num = "";
            for(int j = 0; j < fs.get(i).length(); j++){
                if(fs.get(i).charAt(j) == '.'){
                    fakeIndex++;
                  break;
                } else if(fs.get(i).charAt(j) != ';') {
                    num += fs.get(i).charAt(j);
                } else {
                    checksum += fakeIndex * Integer.parseInt(num);
                    num = "";
                    fakeIndex++;
                }
            }
        }
        System.out.println(checksum);
    }

    public static void partOne(String line) {
        List<String> fs = new ArrayList<>();
        for(int i = 0; i < line.length(); i+=2){
            int file = line.charAt(i) - '0';
            int free = i+1 < line.length() ? line.charAt(i+1) - '0' : 0;
            //System.out.println(file+"and"+free);
            for(int j = 0; j < file; j++){
                //System.out.print(i/2);
                fs.add(String.valueOf(i/2));
            }
            for(int j = 0; j < free; j++){
                //System.out.print('.');
                fs.add(".");
            }
        }
        //System.out.println(fs);

        long checksum = 0;
        while(true){
            checksum = 0;
            boolean found = false;
            for(int i = 0; i < fs.size(); i++){
                String p = fs.get(i);
                if(p.equals(".")){
                   fs.set(i, fs.getLast());
                   fs.removeLast();
                   found = true;
                   break;
                } else {
                    checksum += i * Long.parseLong(p);
                }
            }
            if(!found){
                break;
            }
        }
        //System.out.println(fs);
        System.out.println(checksum);
    }
}