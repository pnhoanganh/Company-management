package utilities;
import java.util.Scanner;

public class Inputter {
    public static Scanner sc = new Scanner(System.in);
    
     // get a double with no condition
    public static double inputDouble(String msg) {
        System.out.print(msg);
        Double data;    
        data = Double.parseDouble(sc.nextLine().trim());
        return data;
    }
    // get an int between min and max
    public static int inputInt(String msg, int min, int max) {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }
        int data;
        do {
            System.out.print(msg);
            data = Integer.parseInt(sc.nextLine());
        } while (data < min || data > max);
        return data;
    }

    // get a string with no condition
    public static String inputStr(String msg) {
        System.out.print(msg);
        String data = sc.nextLine().trim();
        return data;
    }

    // get a non-blank string
    public static String inputNonBlankStr(String msg) {   
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
        } while (data.length() == 0);
        return data;
    }

    // get a string following a pattern
    public static String inputPattern(String msg, String pattern) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
        } while (!data.matches(pattern));
        return data;
    }
}
// Inputter class
