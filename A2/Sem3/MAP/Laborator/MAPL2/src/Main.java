import java.util.Objects;
import java.util.Scanner;

import container.Strategy;
import model.MessageTask;

public class Main {
    private static Strategy strategyFromString(String arg)
    {
        return Strategy.valueOf(arg);
    }

    public static void main(String[] args) {
        TestRunner.runDelay(strategyFromString(args[0]));
        //TestRunner.RunAll();
    }

}