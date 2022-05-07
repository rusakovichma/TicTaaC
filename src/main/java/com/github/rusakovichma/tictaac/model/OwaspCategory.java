package com.github.rusakovichma.tictaac.model;

public enum OwaspCategory {
    brokenAccessControl("A01:2021 - Broken Access Control"),
    cryptographicFailures("A02:2021 - Cryptographic Failures"),
    injection("A03:2021 - Injection"),
    insecureDesign("A04:2021 - Insecure Design"),
    securityMisconfiguration("A05:2021 - Security Misconfiguration"),
    vulnerableAndOutdatedComponents("A06:2021 - Vulnerable and Outdated Components"),
    identificationAndAuthenticationFailures("A07:2021 - Identification and Authentication Failures"),
    softwareAndDataIntegrityFailures("A08:2021 - Software and Data Integrity Failures"),
    securityLoggingAndMonitoringFailures("A09:2021 - Security Logging and Monitoring Failures"),
    serverSideRequestForgery("A10:2021 - Server-Side Request Forgery"),
    undefined("Undefined");

    private final String description;

    OwaspCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
