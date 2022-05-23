package com.lechos22j.wisniamobile.customer;

import java.util.UUID;

public class PersonalCustomer extends Customer {
    public static class Builder {
        private final PersonalCustomer customer;

        public Builder() {
            customer = new PersonalCustomer();
        }

        public Builder setId(UUID id) {
            customer.id = id;
            return this;
        }
        public Builder setPesel(String pesel) {
            customer.pesel = pesel;
            return this;
        }
        public Builder setName(String name) {
            customer.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            customer.surname = surname;
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

        public PersonalCustomer get() {
            return customer;
        }
    }

    private String pesel;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String email;

    public String getPesel() {
        return pesel;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
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
