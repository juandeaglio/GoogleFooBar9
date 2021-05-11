import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;

public class Solution
{
    static HashMap<Integer,BigInteger> factorialHashMap;
    public static String getDistinctConfigurationsOf(int w, int h, int s)
    {
        factorialHashMap = new HashMap<>();
        int totalSymmetries = calcTotalSymmetries(w, h);
        return "-1";
    }

    private static int calcTotalSymmetries(int w, int h)
    {
        return factorial(w).multiply(factorial(h)).intValue();

    }
    private static BigInteger factorial(int n)
    {
        int i = 1;
        BigInteger factorialProd = null;
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
}
