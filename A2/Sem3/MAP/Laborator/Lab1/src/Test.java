import model.NrComplex;
import model.Operation;

public class Test {

    private static void test_FromString(String s, double r, double i)
    {
        NrComplex n1 = NrComplex.fromString(s);
        assert n1.getReal() - r < NrComplex.PRECISION;
        assert n1.getImaginary() - i < NrComplex.PRECISION;
    }

    private static void test_NrComplex(){
        NrComplex a = new NrComplex(1, 0);
        assert !a.equals(1);
        assert a.toString().equals("1");
        a.setImaginary(-1);
        assert a.toString().equals("1-i");
        a.setImaginary(2);
        assert a.toString().equals("1+2*i");
        a.setReal(0);
        assert a.toString().equals("2*i");

        test_FromString("1+2*i", 1, 2);
        test_FromString("1-2*i", 1, -2);
        test_FromString("1-i", 1, -1);
        test_FromString("1+i", 1, +1);
        test_FromString("i", 0, 1);
        test_FromString("-i", 0, -1);
        test_FromString("2*i", 0, 2);
        test_FromString("-2*i", 0, -2);
        test_FromString("1", 1, 0);
        test_FromString("10", 1, 10);
    }

    private static void test_Operation(){
        Operation op = new Operation('+', 2, 2);

    }

    public static void Run(){
        test_NrComplex();
    }
}
