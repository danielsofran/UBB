package app.template.utils;

public class Comparer {
    public static boolean equal(Double a, Double b)
    {
        return Math.abs(a - b)<1e-5;
    }
}
