import java.util.LinkedList;
import java.util.List;

public class Triangle {
    private static long getGeneration(long k){
        long rez = 0;
        while (k > 0)
        {
            k /= 2;
            rez++;
        }
        return rez;
    }

    public static GeneratedTriangles findTheSmallestAndLargestNumbers(long k)
    {
        long gen = getGeneration(k);
        long min = (long) Math.pow(2, gen - 1);
        long max = min * 2;
        return new GeneratedTriangles(min, max);
    }

    public static List<Long> findTheTrianglePath(long k)
    {
        long gen = getGeneration(k);
        long start = (long) Math.pow(2, gen - 1);
        long end = start * 2;
        List<Long> rez = new LinkedList<>();
        while (start < end)
        {
            rez.add(start);
            ++start;
        }
        return rez;
    }
}
