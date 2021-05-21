import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution
{
    static HashMap<Integer,BigInteger> factorialHashMap;
    static HashSet<String> swaps;
    public static String getDistinctConfigurationsOf(int w, int h, int s)
    {
        BigInteger totalSymmetries = calcTotalSymmetries(w, h);
        return "-1";
    }

    public static BigInteger calcTotalSymmetries(int w, int h)
    {
        return factorial(w).multiply(factorial(h));
    }
    private static BigInteger factorial(int n)
    {
        int i = 1;
        BigInteger factorialProd = null;
        if(factorialHashMap == null)
            factorialHashMap = new HashMap<>();

        if(!factorialHashMap.containsKey(n))
        {
            if (factorialHashMap.size() > 0)
            {
                factorialHashMap.get(factorialHashMap.size() - 1);
                i = factorialHashMap.size() - 1;
            }
            for (; i <= n; i++)
            {
                if (i != 1)
                {
                    factorialProd = new BigInteger(Integer.toString(i));
                    factorialProd = factorialProd.multiply(factorialHashMap.get(i - 1));
                    factorialHashMap.put(i, factorialProd);
                }
                else
                {
                    factorialProd = new BigInteger("1");
                    factorialHashMap.put(1, factorialProd);
                }
            }
        }
        else
            factorialProd = factorialHashMap.get(n);
        return factorialProd;
    }

    public static int[][] PartitionOf(int w)
    {
        ArrayList<int[]> partitions = new ArrayList();
        int[] currPartition = {w};
        int indexPicked = 0;
        int valueAtIndex = currPartition[indexPicked];
        partitions.add(currPartition);
        while(valueAtIndex != 1)
        {
            indexPicked = FindLargestNonOneIndex(currPartition);
            valueAtIndex = currPartition[indexPicked];
            currPartition = FindPartitionSuccessor(currPartition, indexPicked);
            partitions.add(currPartition);
        }
        return (int[][]) partitions.toArray();
    }

    private static int[] FindPartitionSuccessor(int[] currPartition, int indexPicked)
    {
        int[] newPartition;
        if(indexPicked == currPartition.length-1 || indexPicked != currPartition.length-2
                || (indexPicked == currPartition.length-2 && currPartition[indexPicked] - 1 <= currPartition[indexPicked+1]+1))
        {
            newPartition = new int[currPartition.length + 1];
            newPartition[indexPicked] = currPartition[indexPicked]-1;
            newPartition[indexPicked+1] = 1;
            for(int i = indexPicked+1; i < currPartition.length; i++)
            {
                newPartition[i+2] = currPartition[i];
            }
            for(int i = 0; i < indexPicked;i++)
            {
                newPartition[i] = currPartition[i];
            }
        }
        else
        {
            newPartition = currPartition.clone();
            newPartition[indexPicked] = currPartition[indexPicked]-1;
            newPartition[indexPicked+1] += 1;
        }
        return newPartition;
    }

    private static int FindLargestNonOneIndex(int[] currPartition)
    {
        int currentVal = 1;
        int indexChosen = 0;
        for(int i = currPartition.length-1; i > 0 && indexChosen == 0; i++)
        {
            currentVal = currPartition[i];
            if(currentVal > 1)
                indexChosen = i;
        }
        return indexChosen;
    }
}
