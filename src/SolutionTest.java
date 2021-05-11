import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest
{
    @Test
    public void FindSymmetriesIn2x3MatrixWith4Colors()
    {
        int w = 3;
        int h = 2;
        int s = 4;
        String expectedAnswer = "430";
        String actualAnswer = Solution.getDistinctConfigurationsOf(w,h,s);
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }

}
