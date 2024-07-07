package app.template.utils.observer;

import app.template.utils.events.Event;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObservable {
    private final List<Observer> observers = new LinkedList<>();
    public void addObserver(Observer e){
        observers.add(e);
    }

    public final void addAll(Observer... observers){
        this.observers.addAll(Arrays.asList(observers));
    }
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    public void removeAll(){
        observers.clear();
    }
    public void notifyObservers(){
        observers.forEach(Observer::update);
    }
}