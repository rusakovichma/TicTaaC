/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.model;

public enum OwaspCategory {
    brokenAccessControl("A01:2021 - Broken Access Control", "Broken Access Control"),
    cryptographicFailures("A02:2021 - Cryptographic Failures", "Cryptographic Failures"),
    injection("A03:2021 - Injection", "Injection"),
    insecureDesign("A04:2021 - Insecure Design", "Insecure Design"),
    securityMisconfiguration("A05:2021 - Security Misconfiguration", "Security Misconfiguration"),
    vulnerableAndOutdatedComponents("A06:2021 - Vulnerable and Outdated Components", "Vulnerable and Outdated Components"),
    identificationAndAuthenticationFailures("A07:2021 - Identification and Authentication Failures", "Identification and Authentication Failures"),
    softwareAndDataIntegrityFailures("A08:2021 - Software and Data Integrity Failures", "Software and Data Integrity Failures"),
    securityLoggingAndMonitoringFailures("A09:2021 - Security Logging and Monitoring Failures", "Security Logging and Monitoring Failures"),
    serverSideRequestForgery("A10:2021 - Server-Side Request Forgery", "Server-Side Request Forgery"),
    undefined("Undefined", "Undefined");

    private final String description;
    private final String shortDescription;

    OwaspCategory(String description, String shortDescription) {
        this.description = description;
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
