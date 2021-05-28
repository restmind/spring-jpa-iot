package com.shuplat.spring.DTO;


public class OrdersDTO {
    private int id;
    private String clientName;
    private String serviceName;
    private String gymLocation;

    public OrdersDTO(int id, String clientName, String serviceName, String gymLocation) {
        this.id = id;
        this.clientName = clientName;
        this.serviceName = serviceName;
        this.gymLocation = gymLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(String gymLocation) {
        this.gymLocation = gymLocation;
    }
}
