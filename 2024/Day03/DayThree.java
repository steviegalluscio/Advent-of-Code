import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DayThree {
    // States  
    private static boolean HAS_M = false;
    private static boolean HAS_U = false;
    private static boolean HAS_L = false;
    private static boolean HAS_LPAREN = false;
    private static boolean IN_MUL_LHS = false;
    private static boolean IN_MUL_RHS = false;
    private static boolean END_MUL = false;
    // Part 2 additional
    private static boolean HAS_D = false;
    private static boolean HAS_O = false;
    private static boolean HAS_N = false;
    private static boolean HAS_AP = false;

    public static void main(String [] args) throws IOException{
        Path filePath = Paths.get("input.txt");
        // Read input into String      
        String input = Files.readString(filePath);

        partOne(input);
        partTwo(input);
    }

    public static void reset(){
        //Reset
        HAS_M = false;
        HAS_U = false;
        HAS_L = false;
        HAS_LPAREN = false;
        IN_MUL_LHS = false;
        IN_MUL_RHS = false;
        END_MUL = false;
        HAS_D = false;
        HAS_O = false;
        HAS_N = false;
        HAS_AP = false;
    }

    // Worst case time complexity: O(n)
    // Top-down LL parser
    public static void partOne(String input) throws IOException{
        reset();
        long result = 0;
        char [] chars = input.toCharArray();

        int i = 0;
        while(i < chars.length){
            if(chars[i] == 'm'){
                HAS_M = true;
            } else if(HAS_M && chars[i] == 'u'){
                HAS_U = true;
            } else if(HAS_U && chars[i] == 'l'){
                HAS_L = true;
            } else if(HAS_L && chars[i] == '('){
                HAS_LPAREN = true;
            } else if(HAS_LPAREN && Character.isDigit(chars[i])){
                IN_MUL_LHS = true;
                int j = i;
                String lhs = "";
                while(IN_MUL_LHS && j < chars.length){
                    if(Character.isDigit(chars[j])){
                        lhs += chars[j];
                        IN_MUL_LHS = true;
                    } else if(chars[j] == ','){
                        IN_MUL_LHS = false;
                        IN_MUL_RHS = true;
                    } else {
                        IN_MUL_LHS = false;
                    }
                    j++;
                }
                String rhs = "";
                while(IN_MUL_RHS && j < chars.length){
                    if(Character.isDigit(chars[j])){
                        rhs += chars[j];
                        IN_MUL_RHS = true;
                    } else if(chars[j] == ')'){
                        IN_MUL_RHS = false;
                        END_MUL = true;
                    } else {
                        IN_MUL_RHS = false;
                    }
                    j++;
                }

                if(END_MUL){
                    i = j - 1;
                    result += Integer.valueOf(lhs) * Integer.valueOf(rhs);
                    //Reset
                    reset();
                }
            } else {
                //Reset
                reset();
            }
            i++;
        }
        System.out.println(result);
    }

    // Worst case time complexity: O(n)
    // Same top-down LL parser but with additional tokens/states
    public static void partTwo(String input) throws IOException{
        reset();
        long result = 0;      
        char [] chars = input.toCharArray();

        int doInt = 1;

        int i = 0;
        while(i < chars.length){
            if(chars[i] == 'm'){
                HAS_M = true;
            } else if(HAS_M && chars[i] == 'u'){
                HAS_U = true;
            } else if(HAS_U && chars[i] == 'l'){
                HAS_L = true;
            } else if(HAS_L && chars[i] == '('){
                HAS_LPAREN = true;
            } else if(HAS_LPAREN && Character.isDigit(chars[i])){
                IN_MUL_LHS = true;
                int j = i;
                String lhs = "";
                while(IN_MUL_LHS && j < chars.length){
                    if(Character.isDigit(chars[j])){
                        lhs += chars[j];
                        IN_MUL_LHS = true;
                    } else if(chars[j] == ','){
                        IN_MUL_LHS = false;
                        IN_MUL_RHS = true;
                    } else {
                        IN_MUL_LHS = false;
                    }
                    j++;
                }
                String rhs = "";
                while(IN_MUL_RHS && j < chars.length){
                    if(Character.isDigit(chars[j])){
                        rhs += chars[j];
                        IN_MUL_RHS = true;
                    } else if(chars[j] == ')'){
                        IN_MUL_RHS = false;
                        END_MUL = true;
                    } else {
                        IN_MUL_RHS = false;
                    }
                    j++;
                }
                if(END_MUL){
                    i = j - 1;
                    result += Integer.valueOf(lhs) * Integer.valueOf(rhs) * doInt;
                    //Reset
                    reset();
                }
            } else if (chars[i] == 'd'){
                HAS_D = true;
            } else if (HAS_D && chars[i] == 'o' ){
                HAS_O = true;
            } else if (HAS_O && (chars[i] == 'n' || chars[i] == '(')){
                if(i+1 < chars.length && chars[i] == '(' && chars[i+1] == ')'){
                    doInt = 1;
                    i++;
                    //Reset
                    reset();
                } else if (chars[i] == 'n') {
                    HAS_N = true;
                }
            } else if (HAS_N && chars[i] == '\''){
                HAS_AP = true;

            } else if (HAS_AP && chars[i] == 't'){
                if(i+2 < chars.length && chars[i+1] == '(' && chars[i+2] == ')'){
                    doInt = 0;
                    i += 2;
                    //Reset
                    reset();
                }
            } else {
                //Reset
                reset();
            }
            i++;
        }
        System.out.println(result);
    }
}
