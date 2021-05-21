import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

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
    public void FindDistinctNonEquivConfigurationsOf2x3MatrixWith4Colors()
    {
        int w = 3;
        int h = 2;
        int s = 4;
        String expectedAnswer = "430";
        String actualAnswer = Solution.getDistinctConfigurationsOf(w,h,s);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void FindDistinctNonEquivConfigurationsOf2x2MatrixWith2Colors()
    {
        int w = 2;
        int h = 2;
        int s = 2;
        String expectedAnswer = "7";
        String actualAnswer = Solution.getDistinctConfigurationsOf(w,h,s);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void FindPartitionOf()
    {
        int w = 4;
        int[][] expectedAnswer = {
                {4},
                {3,1},
                {2,2},
                {2,1,1},
                {1,1,1,1},
        };
        int[][] actualAnswer = Solution.PartitionOf(w);
        Assertions.assertArrayEquals(expectedAnswer, actualAnswer);
    }
}
