package com.lechos22j.wisniamobile.extraservices;

import java.util.UUID;

public class Service {
    private UUID id;
    private String name;
    private String description;
    private String price;
    private String duration;
    private String type;

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public String getDuration() {
        return duration;
    }
    public String getType() {
        return type;
    }
}
