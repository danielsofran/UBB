package app.template.service;

import app.template.orm.ConnectionManager;

public class Service {
    ConnectionManager connectionManager;

    public Service(ConnectionManager connectionManager)
    {
        this.connectionManager = connectionManager;
    }
}
