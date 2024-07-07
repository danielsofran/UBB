package app.template.utils.observer;

import app.template.utils.events.Event;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class MyAbstractObservable<E extends Event> {
    private final List<MyObserver<E>> observers = new LinkedList<>();
    public void addObserver(MyObserver<E> e){
        observers.add(e);
    }

    public void removeObserver(MyObserver<E> e){
        observers.remove(e);
    }
    public void notifyObservers(E t){
        observers.forEach(x->x.update(t));
    }
}
