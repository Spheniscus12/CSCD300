import java.io.FileNotFoundException;

public class BinarySearchRange {

    public static void main(String[] args) throws FileNotFoundException {
        
        String file = args[0];
        double low = Integer.parseInt(args[1]);
        double high = Integer.parseInt(args[2]);
        double[] array = null;

        progMethods pm = new progMethods();

        try {
            array = pm.writeArray(file);
        } catch (FileNotFoundException e) {
        }

        double s = pm.BinarySearchS(array, low);
        double t = pm.BinarySearchT(array, high);

        if (s == -1 || t == -1) {
            System.out.println("No elements found in range");
            return;
        }

        System.out.println("A[" + (int) s + ".." + (int) t + "]");

        }
    }
