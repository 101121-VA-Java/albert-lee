public class Calculator {
    public static void main(String[] args) {
       int a = 1;
       int b = 2;
       int c = add(a, b);
       print("1 + 2 = " + c);
       print(weekday("afawf"));
       print(weekday("Monday"));
       countdown(3);
       greeting("Albert");
    }
    
    public static void print(Object inputString){
        System.out.println(inputString);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b){
        return a - b;
    }

    public static double multiply(double a, double b){
        return a * b;
    }

    public static int remainder(int a, int b){
        return a % b;
    }

    public static String weekday(String inputString){
        switch (inputString) {
            case "Monday":
                break;
            case "Tuesday":
                break;
            case "Wednesday":
                break;
            case "Thursday":
                break;
            case "Friday":
                break;
            default:
                inputString = "something else, definitely not a weekday";
                break;
        }
        return "This should be " + inputString;
    }

    public static void countdown(int startValue) {
        if(startValue < 0) System.out.println("input must be positive or 0");
        while(startValue >= 0){
            print(startValue);
            startValue--;
        }
    }

    public static void greeting(String name){
        print("Hello " + name);
    }
}