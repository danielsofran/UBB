package app.template.utils.events;

public class ChangedEvent<E> implements Event {
    private E data, oldData;
    private ChangeEventType type;

    public ChangedEvent(ChangeEventType type, E data){
        this.type = type;
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public E getOldData() {
        return oldData;
    }

    public ChangeEventType getType() {
        return type;
    }
}