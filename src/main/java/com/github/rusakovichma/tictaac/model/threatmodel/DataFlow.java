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
package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.DefaultValue;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.FlowSource;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.FlowTarget;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.AccountManagement;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.AuthenticationMethod;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.Authorization;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.Encryption;

import java.util.LinkedList;

public class DataFlow {

    private String title;

    private AuthenticationMethod authenticationMethod = AuthenticationMethod.undefined;
    private Authorization authorization = Authorization.undefined;
    private Encryption encryption = Encryption.undefined;
    private AccountManagement accountManagement = AccountManagement.undefined;
    @DefaultValue("true")
    private Boolean inScope;

    @Ref(rootCollection = "assets")
    private LinkedList<Asset> transferredAssets = new LinkedList<>();

    @FlowSource
    private Element source;

    @FlowTarget
    private Element target;

    public AuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public AccountManagement getAccountManagement() {
        return accountManagement;
    }

    public void setAccountManagement(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public LinkedList<Asset> getTransferredAssets() {
        return transferredAssets;
    }

    public void setTransferredAssets(LinkedList<Asset> transferredAssets) {
        this.transferredAssets = transferredAssets;
    }

    public Element getSource() {
        return source;
    }

    public void setSource(Element source) {
        this.source = source;
    }

    public Element getTarget() {
        return target;
    }

    public void setTarget(Element target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getInScope() {
        return inScope;
    }

    public void setInScope(Boolean inScope) {
        this.inScope = inScope;
    }
}
