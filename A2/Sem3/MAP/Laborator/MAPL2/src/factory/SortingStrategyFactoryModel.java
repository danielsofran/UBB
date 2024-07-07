package factory;

import container.AbstractSorter;
import container.SortingStrategy;

public interface SortingStrategyFactoryModel {
    AbstractSorter<Integer> createSortingStrategy(SortingStrategy strategy);
}
