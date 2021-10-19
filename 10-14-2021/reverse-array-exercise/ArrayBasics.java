public class ArrayBasics {
    public static void main(String[] args) {
        String[] test =  {"h", "e", "l", "l", "o"};
        String[] reversedStringArray = reverse(test);
        String finalStringToPrint = "";
        for(int i = 0; i < reversedStringArray.length; i++){
            finalStringToPrint = finalStringToPrint + reversedStringArray[i];   
        }
        System.out.println(finalStringToPrint);
    }

    public static String[] reverse(String[] inputStringToReverse){
        String[] result = new String[inputStringToReverse.length];
        for(int i = inputStringToReverse.length - 1; i >= 0; i--){
            result[inputStringToReverse.length - 1 - i] = inputStringToReverse[i];
        }
        return result;
    }
}