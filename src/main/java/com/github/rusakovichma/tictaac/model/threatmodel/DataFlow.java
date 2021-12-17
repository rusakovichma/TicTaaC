package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.AccountManagement;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.AuthenticationMethod;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.Authorization;
import com.github.rusakovichma.tictaac.model.threatmodel.dataflow.Encryption;

import java.util.Set;

public class DataFlow {

    private AuthenticationMethod authenticationMethod;
    private Authorization authorization;
    private Encryption encryption;
    private AccountManagement accountManagement;

    @Ref(rootCollection = "assets")
    private Set<Asset> transferredAssets;

    @Ref(rootCollection = "elements")
    private Element source;
    @Ref(rootCollection = "elements")
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

    public Set<Asset> getTransferredAssets() {
        return transferredAssets;
    }

    public void setTransferredAssets(Set<Asset> transferredAssets) {
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
}
