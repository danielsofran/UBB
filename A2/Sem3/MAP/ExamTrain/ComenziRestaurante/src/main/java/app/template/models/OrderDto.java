package app.template.models;

import java.time.LocalDateTime;

public class OrderDto {
    Integer tableId;
    LocalDateTime data;
    String numeProduseComandate;

    public OrderDto(Integer tableId, LocalDateTime data, String numeProduseComandate) {
        this.tableId = tableId;
        this.data = data;
        this.numeProduseComandate = numeProduseComandate;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNumeProduseComandate() {
        return numeProduseComandate;
    }

    public void setNumeProduseComandate(String numeProduseComandate) {
        this.numeProduseComandate = numeProduseComandate;
    }
}
