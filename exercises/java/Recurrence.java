import java.util.HashSet;
public class Recurrence {
    public static void main(String[] args) {
        char[] ca = {'a', 'b', 'c', 'c'};
        HashSet<Character> hs = new HashSet<Character>();
        for (int i = 0; i < ca.length; i++) {
            if(hs.contains(ca[i])){
                System.out.println("recurrence found");
                return;
            } else {
                hs.add(ca[i]);
            }
        }
        System.out.println("recurrence not found");
        return;
    }
}
