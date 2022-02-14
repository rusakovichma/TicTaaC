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

    private AuthenticationMethod authenticationMethod;
    private Authorization authorization;
    private Encryption encryption;
    private AccountManagement accountManagement;
    @DefaultValue("true")
    private Boolean inScope;

    @Ref(rootCollection = "assets")
    private LinkedList<Asset> transferredAssets;

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
