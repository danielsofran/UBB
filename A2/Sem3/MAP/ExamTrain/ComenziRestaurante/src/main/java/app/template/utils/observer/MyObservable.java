package app.template.utils.observer;

import app.template.utils.events.Event;

public interface MyObservable <E extends Event> {
    void addObserver(MyObserver<E> e);
    void removeObserver(MyObserver<E> e);
    void notifyObservers(E t);
}