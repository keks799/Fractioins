/**
 * Created by keks on 06.10.14.
 *
 * Enumerator for numbers more than 9
 */
public enum LettersEnum {
    A (1, "A"),
    B (2, "B"),
    C (3, "C"),
    D (4, "D"),
    E (5, "E"),
    F (6, "F"),
    G (7, "G"),
    H (8, "H"),
    I (9, "I"),
    J (10, "J"),
    K (11, "K"),
    L (12, "L"),
    M (13, "M"),
    N (14, "N"),
    O (15, "O"),
    P (16, "P"),
    Q (17, "Q"),
    R (18, "R"),
    S (19, "S"),
    T (20, "T"),
    U (21, "U"),
    V (22, "V"),
    W (23, "W"),
    X (24, "X"),
    Y (25, "Y"),
    Z (26, "Z");

    int num;
    String letterName;

    LettersEnum(int num, String letterName){
        this.num = num;
        this.letterName = letterName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }
}
