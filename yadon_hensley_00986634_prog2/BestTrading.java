import java.io.*;
import java.util.*;

public class BestTrading {

    public static void main(String[] args) {

        TradeMethods tm = new TradeMethods();


    }
    
    //Static subclass to hold methods
    static class TradeMethods {

        public double[] writeArray(String fileName) throws FileNotFoundException {


        File file = new File(fileName);

        //This method will use an array list to store the elements. 
        //This will help determine the size of the regular array as well as allow manipulation of them later if needed.
        ArrayList<Double> tempList = new ArrayList<>();

        if (!file.exists()) {

            throw new FileNotFoundException("File does not exist");

        }

        Scanner numReader = new Scanner(file);

        
        while (numReader.hasNext()) {

            //Ensure other data types are not consumed
            if (numReader.hasNextDouble()) {

                tempList.add(numReader.nextDouble());

            }
            else {
                numReader.next();
            }

        }


        double[] array = new double[tempList.size()];

        //Fill array
        for (int i = 0; i < tempList.size(); i++) {

            array[i] = tempList.get(i);
            
        }

        
        numReader.close();
        return array;

        }

        //Static subclass to create objects that hold data of each BestTrade call
        static class Trade {

            private int buy, sell;
            private double profit;

            Trade(int buy, int sell, double profit) {

                this.buy = buy;
                this.sell = sell;
                this.profit = profit;

            }

            @Override
            public String toString() {
                return "[" + buy + "," + sell + ",$" + profit + "]"; //For printing Trade object to command line: [buy,sell,$profit]
            }
        }

        //Recursive method to find best trade indexes
        public Trade bestTrade(double[] p, int low, int high)  {

            if (low > high) {
                throw new IllegalArgumentException("Low cannot be greater than high");
            }

            if (low == high) {
                return new Trade(low, high, 0); //Buy day = sell day, profit is 0; Base case for recursion to end
            }

            int mid = (low + high) / 2;

            Trade can1 = bestTrade(p, low, mid);
            Trade can2 = bestTrade(p, mid + 1, high);



            return can1;
        }

        //Helper method to return trade object that holds data of: lowest value from first half, highest value from right half, profit
        private Trade bestTradeAcross(double[] p, int low, int high) {

            if (low >= high) {
                throw new IllegalArgumentException("Low cannot be greater than or equal to high");
            }

            int mid = (low + high) / 2;

            int x = low;
            int y = mid + 1;

            for (int i = low + 1; i <= mid; i++) {
                if (p[i] < p[x]) x = i;
            }

            for (int i = mid + 1; i <= high; i++) {
                if (p[i] > p[y]) y = i;
            }

            return new Trade(x,y,p[y] - p[x]);
        }
    }

}