package com.lechos22j.wisniamobile.tariff;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Tariff<Version extends TariffVersion> {
    public static class Builder<Version extends TariffVersion> {
        private Tariff<Version> tariff;

        public Builder() {
            this.tariff = new Tariff<Version>();
        }

        public Builder<Version> setId(UUID id) {
            tariff.id = id;
            return this;
        }
        public Builder<Version> setName(String name) {
            tariff.name = name;
            return this;
        }

        public Tariff<Version> get() {
            return this.tariff;
        }
    }

    private UUID id;
    private String name;
    private final Set<Version> versions;
    private Version lastVersion;

    protected Tariff() {
        id = UUID.randomUUID();
        this.versions = new HashSet<>();
    }

    public void addVersion(Version version) {
        versions.add(lastVersion = version);
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Version> getVersions() {
        return versions;
    }
    public Version getLastVersion() {
        return lastVersion;
    }
}
