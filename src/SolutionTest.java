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
    public void FindPartitionOf4()
    {
        int w = 4;
        int[][] expectedAnswer = {
                {4},
                {3,1},
                {2,2},
                {2,1,1},
                {1,1,1,1},
        };
        int[][] actualAnswer = Solution.partitionOf(w);
        Assertions.assertArrayEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void FindPartitionOf5()
    {
        int w = 5;
        int[][] expectedAnswer = {
                {5},
                {4,1},
                {3,2},
                {3,1,1},
                {2,1,1,1},
                {1,1,1,1,1}
        };
        int[][] actualAnswer = Solution.partitionOf(w);
        Assertions.assertArrayEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenAPartition11111CountAllCyclesInGroupFor4()
    {
        int partitionOf = 4;
        int[] partition = {1,1,1,1};
        int expectedAnswer = 1;
        int actualAnswer = Solution.CountTotalCyclesOfGroupForPartition(partition, partitionOf);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenAPartition32CountAllCyclesInGroupFor5()
    {
        int partitionOf = 5;
        int[] partition = {3,2};
        int expectedAnswer = 20;
        int actualAnswer = Solution.CountTotalCyclesOfGroupForPartition(partition, partitionOf);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenAPartition221CountAllCyclesInGroupFor5()
    {
        int partitionOf = 5;
        int[] partition = {2,2,1};
        int expectedAnswer = 15;
        int actualAnswer = Solution.CountTotalCyclesOfGroupForPartition(partition, partitionOf);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenTwoPartitionsMultiplyGroupsTogether()
    {
        int[] firstPartition = {1,1,1,1};
        int[] secondPartition = {1,1,1,1};
        int states = 4;
        BigInteger expectedAnswer = BigInteger.valueOf(states).pow(16);
        BigInteger actualAnswer = BigInteger.valueOf(states).pow(Solution.MultiplySymmetricGroups(firstPartition,secondPartition));
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenTwoPartitionsFindGroupProduct()
    {
        int[] firstPartition = {1,1,2};
        int[] secondPartition = {2,2};
        int states = 4;
        BigInteger expectedAnswer = BigInteger.valueOf(states).pow(8);
        BigInteger actualAnswer = BigInteger.valueOf(states).pow(Solution.MultiplySymmetricGroups(firstPartition,secondPartition));
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
    @Test
    public void GivenTwoPartitionsFindGroupProductAndCount()
    {
        int dimensions = 4;
        int[] firstPartition = {1,1,2};
        int[] secondPartition = {2,2};
        int states = 4;
        BigInteger expectedAnswer = BigInteger.valueOf(states).pow(8).multiply(BigInteger.valueOf(18));
        int count = Solution.CountTotalCyclesOfGroupForPartition(firstPartition, dimensions) * Solution.CountTotalCyclesOfGroupForPartition(secondPartition, dimensions);
        BigInteger actualAnswer = BigInteger.valueOf(states).pow(Solution.MultiplySymmetricGroups(firstPartition,secondPartition)).multiply(BigInteger.valueOf(count));
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }
}
