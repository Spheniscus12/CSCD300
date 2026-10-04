import java.io.*;
import java.util.*;

public class BestTrading {

    public static void main(String[] args) {

    }
    

    class TradeMethods {

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


        public double BestTrade(double[] p, int low, int high) throws  {

            if (low > high) {

            }

            return 0.0;
        }


    }
}