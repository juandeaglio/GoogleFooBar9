import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class SolutionTest
{
    @Test
    public void FindSymmetriesIn2x3MatrixWith4Colors()
    {
        int w = 3;
        int h = 2;
        BigInteger expectedAnswer = new BigInteger("12");
        BigInteger actualAnswer = Solution.calcTotalSymmetries(w,h);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void Find3Choose2()
    {
        BigInteger expectedAnswer = new BigInteger("3");
        BigInteger actualAnswer = Solution.chooseFrom(3,2);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void FindDistinctNonEquivConfigurationsOf2x3MatrixWith4Colors()
    {
        int w = 3;
        int h = 2;
        int s = 4;
        String expectedAnswer = "430";
        String actualAnswer = Solution.getDistinctConfigurationsOf(w,h,s);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
}
