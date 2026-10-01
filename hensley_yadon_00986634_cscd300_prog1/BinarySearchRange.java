import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class BinarySearchRange {

    public static void main(String[] args) {
        
        //Sanitize command line input
        if (args.length < 3 || args.length > 3) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Program usage: java BinarySearchRange.java <file> <low> <high>");
            return;
        }

        //Parse command line arguments to pass to the functions
        String file = args[0];
        double left = Double.parseDouble(args[1]);
        double right = Double.parseDouble(args[2]);
        double[] array = null;
        
        //Initialize object to execute methods
        progMethods pm = new progMethods();

        //Check the file exists
        try {
            array = pm.writeArray(file);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            return;
        }

        //Perform both searches and assign them to respective variables
        double s = pm.BinarySearchS(array, left);
        double t = pm.BinarySearchT(array, right);

        //Check edge cases
        if (s == -1 || t == -1 || array.length == 0 || s > t || right < array[0] || left > array[array.length - 1]) {
            System.out.println("null");
            return;
        }

        else if (left <= array[0] && right >= array[array.length - 1]) {
            System.out.println("The range is the entire array: " + "A[0" + ".." + (array.length - 1) + "]");
            return;
        }
        
        // System.out.println(Arrays.toString(array));

        System.out.println("A[" + (int) s + ".." + (int) t + "]");

        }
    }

    class progMethods {
    
    //Method to find leftmost occurrence
    public double BinarySearchS(double[] A, double s) {

        double low = 0;    
        double high = A.length - 1;

        while (low <= high) {

            double mid = Math.floor((low + high) / 2);

            //Scan right half if leftmost occurs after mid
            //Here I'm casting mid as an int to ensure the syntax is correct when it accesses A[mid]
            if (A[(int) mid] < s) {
                low = mid + 1;
            }

            //Scan left half if A[mid] is greater, or scan left neighbor if they're equal
            else if (A[(int) mid] >= s) {
                
                //Return if mid is 0 to account for s = 0
                if (mid == 0 || A[(int) (mid - 1)] < s) {
                    return mid;
                }

                //If the neighbor is still greater or equal, keep checking
                else if (A[(int) (mid - 1)] >= s) {
                    high = mid - 1;
                }
            }
        }

        //Return -1 if not found
        return -1;

    }

    //Method to find rightmost occurrence
    public double BinarySearchT(double[] A, double t) {

        double low = 0;    
        double high = A.length - 1;

        while (low <= high) {

            double mid = Math.floor((low + high) / 2);

            //Scan left half if A[mid] is greater 
            if (A[(int) mid] > t) {
                high = mid - 1;
            }

            //Scan right half if A[mid] is greater or scan right neighbor if they're equal
            else if (A[(int) mid] <= t) {
                
                //Return if mid is max length to account for t = A.length - 1
                if (mid == A.length - 1 || A[(int) mid + 1] > t) {
                    return mid;
                }

                //If neighbor is still less than or equal keep checking right
                else if (A[(int) mid + 1] <= t) {
                    low = mid + 1;
                }
            }
        }

        return -1;

    }

    //Method to create an array from given data file
    public double[] writeArray(String fileName) throws FileNotFoundException {


        File file = new File(fileName);

        //This method will use an array list to store the elements. 
        //This will help me determine the size of the regular array as well as allow me to manipulate them later if I need to.
        ArrayList<Double> tempList = new ArrayList<>();

        if (!file.exists()) {

            throw new FileNotFoundException("File does not exist");

        }

        Scanner numReader = new Scanner(file);

        //Scan through file and fill arraylist
        while (numReader.hasNext()) {

            //Ensure other data types are not consumed
            if (numReader.hasNextDouble()) {

                tempList.add(numReader.nextDouble());

            }
            else {
                numReader.next();
            }

        }

        //Initialize array of arraylist size
        double[] array = new double[tempList.size()];

        //Fill array
        for (int i = 0; i < tempList.size(); i++) {

            array[i] = tempList.get(i);
            
        }

        //Close scanner and return
        numReader.close();
        return array;
    }

}