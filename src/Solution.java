import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;
import java.util.*;

public class Solution
{
    static HashMap<Integer,BigInteger> factorialHashMap;
    static HashSet<String> swaps;
    public static String getDistinctConfigurationsOf(int w, int h, int s)
    {
        BigInteger totalSymmetries = calcTotalSymmetries(w, h);
        int[][] colPartitions = partitionOf(w);
        int[][] rowPartitions = partitionOf(h);
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < colPartitions.length; i++)
        {
            for(int j = 0; j < rowPartitions.length; j++)
            {
                int[] firstPartition = colPartitions[i];
                int[] secondPartition = rowPartitions[j];
                int count = Solution.CountTotalCyclesOfGroupForPartition(firstPartition, w) * Solution.CountTotalCyclesOfGroupForPartition(secondPartition, h);
                result = result.add(BigInteger.valueOf(s).pow(Solution.MultiplySymmetricGroups(firstPartition, secondPartition)).multiply(BigInteger.valueOf(count)));
            }
        }
        return result.divide(totalSymmetries).toString();
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

    public static int[][] partitionOf(int w)
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
            for (int i = indexPicked + 1; i < currPartition.length; i++)
            {
                newPartition[i + 1] = currPartition[i];
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

    public static int CountTotalCyclesOfGroupForPartition(int[] partition, int partitionOf)
    {
        BigInteger result = factorial(partitionOf);
        int currVal = partition[partition.length-1];
        int count = 1;
        for(int i = partition.length-2; i >= 0; i--)
        {
            if(currVal != partition[i])
            {
                result = result.divide(BigInteger.valueOf((long)Math.pow(currVal,count)));
                result = result.divide(factorial(count));
                count = 1;
                currVal = partition[i];
            }
            else
            {
                count++;
            }
        }
        result = result.divide(BigInteger.valueOf((long)Math.pow(currVal,count)));
        result = result.divide(factorial(count));
        return result.intValue();
    }
    public static int gcd(int a, int b)
    {
        int gcd = -1;
        for(int i = 1; i <= a && i <= b; i++)
        {
            if(a%i==0 && b%i==0)
                gcd = i;
        }
        return gcd;
    }

    public static int MultiplySymmetricGroups(int[] firstPartition, int[] secondPartition)
    {
        int symmGroupCount = 0;
        for(int i = 0; i < firstPartition.length; i++)
        {
            for(int j = 0; j < secondPartition.length; j++)
            {
                symmGroupCount += gcd(firstPartition[i], secondPartition[j]);
            }
        }
        return symmGroupCount;
    }
}
