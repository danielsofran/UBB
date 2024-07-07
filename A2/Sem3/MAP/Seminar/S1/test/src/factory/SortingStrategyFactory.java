package factory;

import container.AbstractSorter;
import container.NumericBubbleSorter;
import container.NumericMergeSorter;
import container.SortingStrategy;

import static container.SortingStrategy.BubbleSort;
import static container.SortingStrategy.MergeSort;

public class SortingStrategyFactory implements SortingStrategyFactoryModel {
    private static SortingStrategyFactory instance = new SortingStrategyFactory();
    private SortingStrategyFactory(){}

    public static SortingStrategyFactory getInstance(){
        return instance;
    }

    @Override
    public AbstractSorter<Integer> createSortingStrategy(SortingStrategy strategy) {
        switch (strategy) {
            case BubbleSort: return new NumericBubbleSorter();
            case MergeSort: return new NumericMergeSorter();
        }
        return null;
    }
}
