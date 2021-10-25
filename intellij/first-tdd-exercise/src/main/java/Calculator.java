import exceptions.CalculatorException;

public class Calculator {

    /*
     * Should be able to
     * 	- add
     * 	- subtract
     * 	- multiply
     * 	- divide
     * 		- throw Calculator Exception (Runtime) when attempting to divide by 0
     *	- isPrime: check if a number is Prime
     */

    static double add(double x, double y) {
        return x + y;
    }

    static double subtract(double x, double y) {
        return x - y;
    }

    static double multiply(double x, double y) {
        return x * y;
    }

    static double divide(double x, double y) throws Exception{
        if(y == 0) {
            throw new CalculatorException();
        }
        return x / y;
    }

    static boolean isPrime(int x){
        if(x <= 1) return false;
        int checkAll = x - 1;
        while(checkAll > 1) {
            if(x % checkAll == 0) return false;
            checkAll--;
        }
        return true;
    }
}
