package com.shuplat.spring.domain;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "mydb")
public final class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "client_id", insertable = false, updatable = false)
    private int clientId;

    @Column(name = "service_id", insertable = false, updatable = false)
    private int serviceId;

    @Column(name = "gym_id", insertable = false, updatable = false)
    private int gymId;

    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id", nullable = false)
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private Service service;

    public Orders(final int id, final int clientId, final int serviceId, final int gymId) {
        this.id = id;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.gymId = gymId;
    }

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(final int serviceId) {
        this.serviceId = serviceId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(final int gymId) {
        this.gymId = gymId;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return id + " " + clientId + " " + serviceId + " " + gymId;
    }
}

