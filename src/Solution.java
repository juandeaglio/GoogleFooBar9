import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Solution
{
    static HashMap<Integer,BigInteger> factorialHashMap;
    static HashMap<Integer, int[][]> currPartition;
    public static String getDistinctConfigurationsOf(int w, int h, int s)
    {
        BigInteger totalSymmetries = calcTotalSymmetries(w, h);
        int[][] colPartitions = decompose(w);
        int[][] rowPartitions = decompose(h);
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < colPartitions.length; i++)
        {
            for(int j = 0; j < rowPartitions.length; j++)
            {
                int[] firstPartition = colPartitions[i];
                int[] secondPartition = rowPartitions[j];
                BigInteger count = Solution.CountTotalCyclesOfGroupForPartition(firstPartition, w).multiply(Solution.CountTotalCyclesOfGroupForPartition(secondPartition, h));
                result = result.add(BigInteger.valueOf(s).pow(Solution.MultiplySymmetricGroups(firstPartition, secondPartition)).multiply(count));
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
    public static int[][] partition(int n)
    {
        int[][] toAdd = {{1}};
        currPartition.put(1, toAdd);
        return decompose(n);
    }
    public static int[][] appendArray(int r, int[] toAppend, int[][] array)
    {
        int sal = array.length;   //determines length of secondArray
        int[][] result = new int[1 + sal][];  //resultant array of size first array and second array
        System.arraycopy(array, 0, result, 0, sal);
        int[] newArr = new int[toAppend.length+1];
        System.arraycopy(toAppend, 0, newArr, 1, toAppend.length);
        newArr[0] = r;
        result[array.length] = newArr;
        return result;
    }
    public static int[][] decompose(int n)
    {
        if(currPartition == null)
            currPartition = new HashMap<>();
        if(currPartition.containsKey(n))
            return currPartition.get(n);
        int[] toAdd = new int[1];
        toAdd[0] = n;
        int[][] result = new int[1][];
        result[0] = toAdd;
        int partitionCurrVal;

        for(int i = 1; i < n; i++)
        {
            partitionCurrVal = n - i;
            int[][] recursivePartition = decompose(i);
            for(int r = 0; r < recursivePartition.length; r++)
                if (recursivePartition[r][0] <= partitionCurrVal)
                    result = appendArray(partitionCurrVal, recursivePartition[r], result);
        }

        currPartition.put(n, result);
        return result;
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
    public static BigInteger CountTotalCyclesOfGroupForPartition(int[] partition, int partitionOf)
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
        return result;
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
