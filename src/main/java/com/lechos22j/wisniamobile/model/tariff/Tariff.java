package com.lechos22j.wisniamobile.model.tariff;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Tariff {
    public static class Builder {
        private Tariff tariff;

        public Builder() {
            this.tariff = new Tariff();
        }

        public Builder setId(UUID id) {
            tariff.id = id;
            return this;
        }
        public Builder setName(String name) {
            tariff.name = name;
            return this;
        }

        public Tariff get() {
            return this.tariff;
        }
    }

    private UUID id;
    private String name;
    private Set<TariffVersion> versions;
    private TariffVersion lastVersion;

    protected Tariff() {
        id = UUID.randomUUID();
        this.versions = new HashSet<>();
    }

    public void addVersion(TariffVersion version) {
        versions.add(lastVersion = version);
    }
    public void setVersions(Set<TariffVersion> versions) {
        this.versions = versions;
    }
    public void setLastVersion(TariffVersion lastVersion) {
        this.lastVersion = lastVersion;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<TariffVersion> getVersions() {
        return versions;
    }
    public TariffVersion getLastVersion() {
        return lastVersion;
    }
}
