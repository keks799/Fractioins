import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by keks on 29.09.14.
 *
 * Calculation class
 */
public class Calculator {

    /**
     * main calculation activity
     *
     * @return final result
     */
    public static String calculate(Integer[] inputArgs){
        // list of fraction entity. Ready to calculate.
        List<Fraction> fractionList = getFractionList(inputArgs);
        if(fractionList == null){
            return null;
        }
        Fraction[] fractions = fractionList.toArray(new Fraction[0]);
        String result = new String();
        for (Fraction fraction : fractions){
            if(fraction.getDivisor() == 1 || fraction.getDivident() == fraction.getDivisor()){ // if the fraction is integer conversion
                if(fraction.getDivident() < fraction.getNotation()){ // if the result will contain numbers higher, then 9
                    if(fraction.getDivident() >= 10){
                        result += LettersEnum.values()[fraction.getDivident() - 10].letterName;
                    } else if(fraction.getDivident() < 10){ // simple result without transformation
                        result += String.valueOf(fraction.getDivident());
                    }
                } else { // if there won't numbers higher than 9
                    result += integer(fraction);
                }
            } else { // fractional activity
                result += fractional(fraction);
            }
            result += "\n";
        }
        return result;
    }

    /**
     * fractional activity
     *
     * @param fraction fraction's entity
     * @return result for fraction
     */
    private static String fractional(Fraction fraction){
        final int RESULT_LENGTH = 10;   // accuracy
        final int NON_ERROR_VALUE = 7;  // number's count without infelicity
        final String FRACTION_SEPARATOR = "0.";

        String result = new String(FRACTION_SEPARATOR);
        boolean isLettersNeeded = false; // if there will be numbers higher than 9
        Set<Double> decimalFractionSet = new LinkedHashSet<Double>(); // temporary set to count how many numbers in period

        Double decimalFraction = (Double.valueOf(fraction.getDivident()) / Double.valueOf(fraction.getDivisor())); // getting pure fraction
        // start of transformation
        decimalFraction = decimalFraction * fraction.getNotation();
        decimalFractionSet.add(new BigDecimal(decimalFraction).setScale(NON_ERROR_VALUE, RoundingMode.DOWN).doubleValue());
        int setSize = decimalFractionSet.size();
        if(fraction.getDivident() < fraction.getNotation() && fraction.getDivident() >= 10){
            isLettersNeeded = true;
        }
        do {
            int beforeComma = new BigDecimal(decimalFraction).setScale(0, RoundingMode.DOWN).intValue(); // integers
            decimalFraction = (decimalFraction - beforeComma) * fraction.getNotation(); // decimals
            if(isLettersNeeded && beforeComma >= 10){
                result += LettersEnum.values()[beforeComma - 10].letterName;
            } else {
                result += String.valueOf(beforeComma);
            }
            double bigDecimalFraction = new BigDecimal(decimalFraction).setScale(NON_ERROR_VALUE, RoundingMode.DOWN).doubleValue();
            decimalFractionSet.add(bigDecimalFraction);
            if(decimalFractionSet.size() == setSize){ // if the period is about to begin
                Iterator it = decimalFractionSet.iterator();
                int i = 0;
                while (it.hasNext()){
                    if(it.next().equals(bigDecimalFraction)){
                        result = result.substring(0, result.length() - setSize + i) + "(" + result.substring(result.length() - setSize + i, result.length());
                        result += ")";
                        return result;
                    } else {
                        i++;
                    }
                }
            }
            setSize = decimalFractionSet.size(); // if the period is not about to begin, continue to collect numbers
        } while(decimalFraction != 0 && result.length() <= RESULT_LENGTH);

        return result;
    }

    /**
     * integer activity
     *
     * @param fraction integer's fraction entity
     * @return result for integer
     */
    private static String integer(Fraction fraction){
        List<String> modulo = new LinkedList<String>(); // list to collecting modulo in order
        int a = fraction.getDivident();
        int k = fraction.getNotation();
        String res = new String();

        if((a / k) < k){
            a = longDivision(modulo, a, k);
        } else {
            do {
                a = longDivision(modulo, a, k);
            } while(a > k);
        }
        res += String.valueOf(a);
        Collections.reverse(modulo);
        for(String str : modulo){
            res += str;
        }
        return res;

    }

    /**
     * long division as in paper
     *
     * @param modulo list to collecting modulo in order
     * @param a fraction's divident
     * @param k notation
     * @return modulo and result of a division
     */
    private static int longDivision(List<String> modulo, int a, int k) {
        modulo.add(String.valueOf(a % k));
        a = a / k;
        return a;
    }

    /**
     * get list of Fraction entity from wiped arguments
     *
     * @return list of entity
     */
    private static List <Fraction> getFractionList(Integer[] inputArgs){
        List<Fraction> fractions = new ArrayList<Fraction>();
        for(int i = 0; i < inputArgs.length + 1 / 3; i++){
            int divident = inputArgs[i];
            int divisor = inputArgs[++i];
            int notation = inputArgs[++i];
            if (divisor == 0){ // divsion by zero check
                return null;
            }
            if (notation < 2 || notation > 36){ // check for correct notation
                return null;
            }
            fractions.add(new Fraction(divident, divisor, notation));
        }
        return fractions;
    }
}
