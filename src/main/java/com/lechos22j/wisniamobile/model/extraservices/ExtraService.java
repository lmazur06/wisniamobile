package com.lechos22j.wisniamobile.model.extraservices;

import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.util.UUID;

public class ExtraService {
    public static class Builder{
        private final ExtraService extraService;

        public Builder(){
            extraService = new ExtraService();
        }

        public Builder setId(UUID id){
            extraService.id = id;
            return this;
        }
        public Builder setName(String name){
            extraService.name = name;
            return this;
        }
        public Builder setDescription(String description){
            extraService.description = description;
            return this;
        }
        public Builder setPrice(double price){
            extraService.price = price;
            return this;
        }
        public Builder setTariffVersion(TariffVersion tariffVersion){
            extraService.tariffVersion = tariffVersion;
            return this;
        }

        public ExtraService get(){
            return extraService;
        }
    }
    private UUID id;
    private String name;
    private String description;
    private double price;
    private TariffVersion tariffVersion;

    private ExtraService(){
        this.id = UUID.randomUUID();
    }

    public void setTariffVersion(TariffVersion tariffVersion) {
        this.tariffVersion = tariffVersion;
    }

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
    public TariffVersion getTariffVersion() {
        return tariffVersion;
    }
}
