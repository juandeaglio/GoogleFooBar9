import java.math.BigInteger;
import java.util.*;

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
            currPartition = FindPartitionSuccessor(currPartition, indexPicked);
            partitions.add(currPartition);
            indexPicked = FindLargestNonOneIndex(currPartition);
            valueAtIndex = currPartition[indexPicked];
        }
        return convertIntegers(partitions);
    }
    public static int[][] convertIntegers(ArrayList<int[]> integers)
    {
        int[][] result = new int[integers.size()][];
        for (int i=0; i < result.length; i++)
        {
            int[] ret = integers.get(i);
            result[i] = ret;
        }
        return result;
    }
    private static int[] FindPartitionSuccessor(int[] currPartition, int indexPicked)
    {
        int[] newPartition;
        if(indexPicked == currPartition.length-2 && currPartition[indexPicked]-1 >= currPartition[indexPicked+1]+1)
        {
            newPartition = currPartition.clone();
            newPartition[indexPicked] = currPartition[indexPicked]-1;
            newPartition[indexPicked+1] += 1;
        }
        else
        {
            newPartition = new int[currPartition.length + 1];
            newPartition[indexPicked] = currPartition[indexPicked]-1;
            newPartition[indexPicked+1] = 1;
            if(indexPicked < currPartition.length-2)
            {
                for (int i = indexPicked + 1; i < currPartition.length; i++) {
                    newPartition[i + 1] = currPartition[i];
                }
            }
            for(int i = 0; i < indexPicked;i++)
            {
                newPartition[i] = currPartition[i];
            }
        }
        return newPartition;
    }

    private static int FindLargestNonOneIndex(int[] currPartition)
    {
        int currentVal = 1;
        int indexChosen = 0;
        for(int i = currPartition.length-1; i >= 0 && indexChosen == 0; i--)
        {
            currentVal = currPartition[i];
            if(currentVal > 1)
                indexChosen = i;
        }
        return indexChosen;
    }
}
