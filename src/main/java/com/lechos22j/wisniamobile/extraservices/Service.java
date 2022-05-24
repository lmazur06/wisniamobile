package com.lechos22j.wisniamobile.extraservices;

import java.util.UUID;

public class Service {
    public static class Builder{
        private final Service service;

        public Builder(){
            service = new Service();
        }

        public Builder setId(UUID id){
            service.id = id;
            return this;
        }
        public Builder setName(String name){
            service.name = name;
            return this;
        }
        public Builder setDescription(String description){
            service.description = description;
            return this;
        }
        public Builder setPrice(double price){
            service.price = price;
            return this;
        }

        public Service get(){
            return service;
        }
    }
    private UUID id;
    private String name;
    private String description;
    private double price;

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
}
