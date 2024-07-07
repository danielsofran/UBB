import container.AbstractSorter;
import container.NumericMergeSorter;
import container.SortingStrategy;
import container.Strategy;
import model.MessageTask;
import model.SortingTask;
import model.Task;
import runner.DelayTaskRunner;
import runner.PrinterTaskRunner;
import runner.StrategyTaskRunner;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestRunner {

    private static Task[] createTasks(){
        MessageTask m1 = new MessageTask("1", "teme laborator",
                "ai primit o tema de laborator", "George", "Maria",
                LocalDateTime.now());
        MessageTask m2 = new MessageTask("2", "teme laborator",
                "ai o tema de seminar", "Mihai", "Maria",
                LocalDateTime.now());
        MessageTask m3 = new MessageTask("3", "teme seminar",
                "ai primit o tema de seminar", "George", "MIhai",
                LocalDateTime.now());
        MessageTask m4 = new MessageTask("4", "cerere bursa",
                "va rog depuneti cererea", "Secretariat", "Daniel",
                LocalDateTime.now());
        MessageTask m5 = new MessageTask("5", "congrats",
                "you completed the 200km running challenge", "Strava @Inc", "Daniel",
                LocalDateTime.now());
        return new MessageTask[]{m1, m2, m3, m4, m5};
    }

    private static Task[] createSortingTasks(){
        SortingTask s1 = new SortingTask("1", "sortare numere",
                new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, SortingStrategy.BubbleSort);
        SortingTask s2 = new SortingTask("1", "sortare numere",
                new Integer[]{34,643,643,24,53546,3456,6}, SortingStrategy.MergeSort);
        SortingTask s3 = new SortingTask("1", "sortare numere",
                new Integer[]{1,4,2,6,4,2,6,4,3}, SortingStrategy.BubbleSort);
        Task[] rez1 = new SortingTask[]{s1,s2,s3};
        return rez1;
    }

    public static void runStrategy(Strategy strategy){
        Task[] messageTasks = createTasks();
        StrategyTaskRunner runner = new StrategyTaskRunner(strategy);
        for(var task : messageTasks)
            runner.addTask(task);
        runner.executeAll();
    }

    public static void runPrinter(Strategy strategy){
        Task[] messageTasks = createTasks();
        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(strategy);
        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner);

        for(Task task : messageTasks)
            printerTaskRunner.addTask(task);

        printerTaskRunner.executeAll();
    }

    public static void runDelay(Strategy strategy){
        Task[] messageTasks = createSortingTasks();
        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(strategy);
        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(strategyTaskRunner);

        for(Task task : messageTasks)
            delayTaskRunner.addTask(task);

        delayTaskRunner.executeAll();
    }

    public static void testAbstractSorter(){
        AbstractSorter<Integer> sorter = new NumericMergeSorter();
        Integer[] v = new Integer[]{25, 11, 33, -1, 23, 10, 0, 55};
        sorter.sort(v);
    }

    public static void RunAll(){
        Task[] lista = createTasks();
        for(Task messageTask : lista)
            messageTask.execute();

        runStrategy(Strategy.LIFO);
        runPrinter(Strategy.LIFO);
        runDelay(Strategy.LIFO);

        runStrategy(Strategy.FIFO);
        runPrinter(Strategy.FIFO);
        runDelay(Strategy.FIFO);

        testAbstractSorter();
    }


}
