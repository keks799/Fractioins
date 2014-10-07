/**
 * Created by keks on 05.10.14.
 *
 * Fraction entity
 */
public class Fraction {
    private int divident;
    private int divisor;
    private int notation;

    public Fraction(int divident, int divisor, int notation){
        this.divident = divident;
        this.divisor = divisor;
        this.notation = notation;
    }

    public int getDivident() {
        return divident;
    }

    public void setDivident(int divident) {
        this.divident = divident;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public int getNotation() {
        return notation;
    }

    public void setNotation(int notation) {
        this.notation = notation;
    }
}
