package com.aipo.orm.model.social.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _OAuth2Token was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _OAuth2Token extends CayenneDataObject {

    public static final String CLIENT_ID_PROPERTY = "clientId";
    public static final String CODE_TYPE_PROPERTY = "codeType";
    public static final String CREATE_DATE_PROPERTY = "createDate";
    public static final String EXPIRE_TIME_PROPERTY = "expireTime";
    public static final String SCOPE_PROPERTY = "scope";
    public static final String TOKEN_PROPERTY = "token";
    public static final String TOKEN_TYPE_PROPERTY = "tokenType";
    public static final String USER_ID_PROPERTY = "userId";

    public static final String TOKEN_ID_PK_COLUMN = "TOKEN_ID";

    public void setClientId(String clientId) {
        writeProperty("clientId", clientId);
    }
    public String getClientId() {
        return (String)readProperty("clientId");
    }

    public void setCodeType(String codeType) {
        writeProperty("codeType", codeType);
    }
    public String getCodeType() {
        return (String)readProperty("codeType");
    }

    public void setCreateDate(Date createDate) {
        writeProperty("createDate", createDate);
    }
    public Date getCreateDate() {
        return (Date)readProperty("createDate");
    }

    public void setExpireTime(Date expireTime) {
        writeProperty("expireTime", expireTime);
    }
    public Date getExpireTime() {
        return (Date)readProperty("expireTime");
    }

    public void setScope(String scope) {
        writeProperty("scope", scope);
    }
    public String getScope() {
        return (String)readProperty("scope");
    }

    public void setToken(String token) {
        writeProperty("token", token);
    }
    public String getToken() {
        return (String)readProperty("token");
    }

    public void setTokenType(String tokenType) {
        writeProperty("tokenType", tokenType);
    }
    public String getTokenType() {
        return (String)readProperty("tokenType");
    }

    public void setUserId(String userId) {
        writeProperty("userId", userId);
    }
    public String getUserId() {
        return (String)readProperty("userId");
    }

}
