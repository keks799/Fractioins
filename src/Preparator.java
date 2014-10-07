import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by keks on 28.09.14.
 *
 * String preparation.
 * Wipes string, removes all signs, which are not digits.
 * Extract prams from the string for calculatoins.
 */
public class Preparator {

    public Preparator(){
    }

    /**
     * checks if thhere are any arguments, if no - waits for console input
     * @param args - console arguments
     * @return clean parameters for calculation
     */
    public Integer[] prepareArgumentsForCalculation(String[] args){
        String[] stringArgs;
        ArrayList<Integer> result = new ArrayList<Integer>();

        if(args.length == 0) {
            System.out.println("Please point divident, divisor in decimal system and notation with space. At the end type \"q\".");
            System.out.println("Or you can point them as the parameters.");
            stringArgs = readFomConsole();
            while(stringArgs.length % 3 != 0){
                System.out.println("Incorrect! There should be divident divisor notation. Three single numbers.");
                stringArgs = readFomConsole();
            }
        } else {
            for(int i = 0; i < args.length; i++){
                args[i] = args[i].replaceAll("\\D", "");
            }
            stringArgs = args;
            if(stringArgs.length > 0 && stringArgs.length % 3 != 0){
                System.out.println("Incorrect! There should be divident divisor notation. Three single numbers.");
                return null;
            }
        }

        for (String str : stringArgs){
            result.add(new Integer(str));
        }
        return result.toArray(new Integer[0]);
    }

    /**
     * console reading mechanism
     *
     * @return clean parameters from keyboard
     */
    private String[] readFomConsole() {
        String[] result;
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        String inputLine = new String();

        while (!(inputLine.contains("q"))){
            try {
                inputLine = inputLine + in.readLine() + "\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        inputLine = inputLine.trim().replaceAll("\\D", " " );
        inputLine = inputLine.replaceAll("\\s+", " ");
        result = inputLine.split(" ");
        return result;
    }
}
