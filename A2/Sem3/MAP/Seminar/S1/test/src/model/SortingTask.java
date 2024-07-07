package model;

import container.AbstractSorter;
import container.SortingStrategy;
import factory.SortingStrategyFactory;

public class SortingTask extends Task{
    private Integer[] vector;
    AbstractSorter<Integer> sorter;

    public SortingTask(String _taskID, String _description, Integer[] vector, SortingStrategy strategy) {
        super(_taskID, _description);
        this.vector = vector;
        sorter = SortingStrategyFactory.getInstance().createSortingStrategy(strategy);
    }

    public Integer[] getVector() {
        return vector;
    }

    public void setVector(Integer[] vector) {
        this.vector = vector;
    }

    private void showVector(){
        for(var x : vector)
            System.out.print(x + " ");
        System.out.println();
    }

    @Override
    public void execute() {
        sorter.sort(vector);
        showVector();
    }
}
