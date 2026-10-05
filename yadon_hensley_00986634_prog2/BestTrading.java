import java.io.*;
import java.util.*;

/**
 * @author Yadon Hensley
 * BestTrading 
 */
public class BestTrading {

    public static void main(String[] args) {

        //Sanitize command line input
        if (args.length > 1) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Program usage: java BestTrading.java <file>");
            return;
        }

        String file = args[0];
        double[] p = null;

        TradeMethods tm = new TradeMethods();

        try {
            p = tm.writeArray(file);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            return;
        }

        if (p.length == 0) {
            System.out.println("Data file is empty");
            return;
        }

        System.out.println(tm.bestTrade(p, 0, p.length - 1));


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
                return "[" + buy + "," + sell + ",$" + String.format("%.2f", profit) + "]"; //For printing Trade object to command line: [buy,sell,$profit]
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

            Trade cand1 = bestTrade(p, low, mid);
            Trade cand2 = bestTrade(p, mid + 1, high);
            Trade cand3 = bestTradeAcross(p, low, high);

            //Compare profit fields to return best trade each time
            if (cand1.profit >= cand2.profit && cand1.profit >= cand3.profit) {
                return cand1;
            }

            else if(cand2.profit >= cand1.profit && cand2.profit >= cand3.profit) {
                return cand2;
            }

            else {
                return cand3;
            }

        }

        //Helper method to return trade object that holds data of: lowest value from left half, highest value from right half, profit
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