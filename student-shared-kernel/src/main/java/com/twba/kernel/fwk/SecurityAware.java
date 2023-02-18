package com.twba.kernel.fwk;

interface SecurityAware {

    void setUserInfo(DomainUser domainUser);
    DomainUser extractUserInfo();
    void setSecurityToken(String securityToken);
    String getSecurityToken();

}
