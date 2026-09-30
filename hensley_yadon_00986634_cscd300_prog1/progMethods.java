import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class progMethods {
    
    public double BinarySearchS(double[] A, double s) {

        double low = 0;    
        double high = A.length - 1;

        while (low <= high) {

            double mid = Math.floor((low + high) / 2);

            if (A[(int) mid] < s) {
                low = mid + 1;
            }

            else if (A[(int) mid] >= s) {
                
                if (A[(int) mid - 1] < s) {
                    return mid;
                }

                else if (A[(int) mid - 1] >= s) {
                    high = mid - 1;
                }
            }
        }

        return -1;

    }

    public double BinarySearchT(double[] A, double t) {

        double low = 0;    
        double high = A.length - 1;

        while (low <= high) {

            double mid = Math.floor((low + high) / 2);

            if (A[(int) mid] > t) {
                high = mid - 1;
            }

            else if (A[(int) mid] <= t) {
                
                if (A[(int) mid + 1] > t) {
                    return mid;
                }

                else if (A[(int) mid + 1] <= t) {
                    low = mid + 1;
                }
            }
        }

        return -1;

    }

    public double[] writeArray(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        double[] array = null;
        ArrayList<Double> tempList = new ArrayList<>();

        if (!file.exists()) {

            throw new FileNotFoundException("File does not exist");

        }

        Scanner numReader = new Scanner(file);

        while (numReader.hasNext()) {

            tempList.add(numReader.nextDouble());

        }

        array = new double[tempList.size()];

        for (int i = 0; i < tempList.size(); i++) {

            array[i] = tempList.get(i);
            
        }

        numReader.close();
        return array;
    }

}
