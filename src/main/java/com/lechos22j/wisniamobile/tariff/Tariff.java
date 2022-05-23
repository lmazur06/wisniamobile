package com.lechos22j.wisniamobile.tariff;

import java.util.HashSet;
import java.util.Set;

public class Tariff<Version extends TariffVersion> {
    private final Set<Version> versions;
    private Version lastVersion;

    protected Tariff() {
        this.versions = new HashSet<>();
    }

    public void addVersion(Version version) {
        versions.add(lastVersion = version);
    }

    public Set<Version> getVersions() {
        return versions;
    }
    public Version getLastVersion() {
        return lastVersion;
    }
}
