package app.template.utils.observer;

import app.template.utils.events.Event;

public interface MyObserver<E extends Event> {
    void update(E e);
}
