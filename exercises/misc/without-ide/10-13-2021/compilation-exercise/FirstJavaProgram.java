public class FirstJavaProgram {
    public static void main(String[] args) {
        double num = 1;
        while(num < 98){
            System.out.println(num);
            num = java.lang.Math.random()*100;
        }
    }
}