package com.lechos22j.wisniamobile.model.customer;

import java.util.UUID;

public class CompanyCustomer extends Customer {
    public static class Builder {
        private final CompanyCustomer customer;

        public Builder() {
            customer = new CompanyCustomer();
        }

        public Builder setId(UUID id) {
            customer.id = id;
            return this;
        }
        public Builder setName(String name) {
            customer.name = name;
            return this;
        }
        public Builder setNip(String nip) {
            customer.nip = nip;
            return this;
        }
        public Builder setAddress(String address) {
            customer.address = address;
            return this;
        }
        public Builder setPhone(String phone) {
            customer.phone = phone;
            return this;
        }
        public Builder setEmail(String email) {
            customer.email = email;
            return this;
        }

        public CompanyCustomer get() {
            return customer;
        }
    }
    private String name;
    private String nip;
    private String address;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }
    public String getNip() {
        return nip;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
}
