package app.template.service;

import app.template.exceptions.ServiceException;
import app.template.models.*;
import app.template.orm.ConnectionManager;
import app.template.repository.Repository;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyAbstractObservable;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Service extends MyAbstractObservable<ChangedEvent<Order>> {
    ConnectionManager connectionManager;
    Repository<Integer, Table> tableRepository;
    Repository<Integer, MenuItem> menuItemRepository;
    Repository<Integer, Order> orderRepository;
    Repository<Integer, OrderItems> orderItemsRepository;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
        tableRepository = new Repository<>(Table.class, connectionManager);
        menuItemRepository = new Repository<>(MenuItem.class, connectionManager);
        orderRepository = new Repository<>(Order.class, connectionManager);
        orderItemsRepository = new Repository<>(OrderItems.class, connectionManager);
    }

    public List<Table> getAllTables()
    {
        return tableRepository.findAll();
    }

    public List<Pair<String, List<MenuItem>>> getMenu(){
        List<MenuItem> menuItems = menuItemRepository.findAll();
        List<String> captions = menuItems.stream().map(MenuItem::getCategory).distinct().collect(Collectors.toList());
        List<Pair<String, List<MenuItem>>> rez = new LinkedList<>();
        captions.forEach(caption -> {
            List<MenuItem> items = menuItems.stream().filter(menuItem -> menuItem.getCategory().equals(caption)).collect(Collectors.toList());
            Pair<String, List<MenuItem>> pair = new Pair<>(caption, items);
            rez.add(pair);
        });
        return rez;
    }

    public void order(Table table, List<MenuItem> items)
    {
        if(items.size() == 0)
            throw new ServiceException("Please select items!");
        Order order = new Order(table, LocalDateTime.now(), OrderStatus.PLACED);
        order = orderRepository.save(order);
        for(MenuItem menuItem : items)
        {
            OrderItems orderItems = new OrderItems(order, menuItem);
            orderItemsRepository.save(orderItems);
        }
        notifyObservers(new ChangedEvent<>(ChangeEventType.ADDED, order));
    }

    public List<OrderDto> getOrders()
    {
        List<OrderDto> rez = new LinkedList<>();
        orderRepository.findAll().forEach(order -> {
            String cereri = orderItemsRepository.findAll().stream()
                    .filter(oi -> oi.getOrder().equals(order))
                    .map(oi -> oi.getMenuItem().getItem())
                    .reduce((a, b) -> a+", "+b).orElse("");
            OrderDto orderDto = new OrderDto(order.getTable().getId(), order.getDate(), cereri);
            rez.add(orderDto);
        });
        return rez;
    }
}
