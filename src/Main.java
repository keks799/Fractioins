public class Main {

    public static void main(String[] args) {
        Integer[] inputArgs;
        Preparator preparator = new Preparator();

        inputArgs = preparator.prepareArgumentsForCalculation(args);
        if(inputArgs != null) {
            String resultString = Calculator.calculate(inputArgs);
            if(resultString != null){
                System.out.println(resultString);
            } else {
                System.out.println("Input data error");
            }
        }
    }
}