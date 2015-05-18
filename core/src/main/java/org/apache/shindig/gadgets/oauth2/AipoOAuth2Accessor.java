/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shindig.gadgets.oauth2;

import java.util.Map;

import org.apache.shindig.common.servlet.Authority;

import com.google.common.collect.Maps;

/**
 *
 * see {@link OAuth2Accessor}
 *
 * @see BasicOAuth2Accessor
 */
public class AipoOAuth2Accessor implements OAuth2Accessor {
  private static final long serialVersionUID = 2050065428260384933L;

  private OAuth2Token accessToken;

  private final boolean allowModuleOverrides;

  private boolean authorizationHeader;

  private String authorizationUrl;

  private String clientAuthenticationType;

  private String clientId;

  private byte[] clientSecret;

  private OAuth2Error error;

  private String errorContextMessage;

  private Throwable errorException;

  private boolean errorResponse;

  private String errorUri;

  private final String gadgetUri;

  private final String globalRedirectUri;

  private final transient Authority authority;

  private final transient String contextRoot;

  private String grantType;

  private boolean redirecting;

  private String redirectUri;

  private OAuth2Token refreshToken;

  private final String scope;

  private final String serviceName;

  private transient OAuth2CallbackState state;

  private String tokenUrl;

  private Type type;

  private boolean urlParameter;

  private final String user;

  private Map<String, String> additionalRequestParams;

  private String[] allowedDomains;

  public AipoOAuth2Accessor() {
    this(null, null, null, null, null, false, null, null, null, null);
  }

  AipoOAuth2Accessor(final Throwable exception, final OAuth2Error error,
      final String contextMessage, final String errorUri) {
    this.serviceName = null;
    this.scope = null;
    this.state = null;
    this.tokenUrl = null;
    this.type = null;
    this.user = null;
    this.gadgetUri = null;
    this.globalRedirectUri = null;
    this.authority = null;
    this.contextRoot = null;
    this.allowModuleOverrides = false;
    this.additionalRequestParams = Maps.newHashMap();
    this.setErrorResponse(exception, error, contextMessage, errorUri);
  }

  public AipoOAuth2Accessor(final OAuth2Accessor accessor) {
    this.accessToken = accessor.getAccessToken();
    this.authorizationUrl = accessor.getAuthorizationUrl();
    this.clientAuthenticationType = accessor.getClientAuthenticationType();
    this.authorizationHeader = accessor.isAuthorizationHeader();
    this.urlParameter = accessor.isUrlParameter();
    this.clientId = accessor.getClientId();
    this.clientSecret = accessor.getClientSecret();
    this.gadgetUri = accessor.getGadgetUri();
    this.grantType = accessor.getGrantType();
    this.redirectUri = accessor.getRedirectUri();
    this.refreshToken = accessor.getRefreshToken();
    this.serviceName = accessor.getServiceName();
    this.scope = accessor.getScope();
    this.state = accessor.getState();
    this.tokenUrl = accessor.getTokenUrl();
    this.type = accessor.getType();
    this.user = accessor.getUser();
    this.allowModuleOverrides = false;
    this.globalRedirectUri = null;
    this.authority = null;
    this.contextRoot = null;
    this.errorResponse = accessor.isErrorResponse();
    this.redirecting = accessor.isRedirecting();
    this.error = accessor.getError();
    this.errorContextMessage = accessor.getErrorContextMessage();
    this.errorException = accessor.getErrorException();
    this.errorUri = accessor.getErrorUri();
    this.additionalRequestParams = Maps.newHashMap();
    this.allowedDomains = accessor.getAllowedDomains();
  }

  public AipoOAuth2Accessor(final String appId, final String gadgetUri,
      final String serviceName, final String user, final String scope,
      final boolean allowModuleOverrides, final OAuth2Store store,
      final String globalRedirectUri, final Authority authority,
      final String contextRoot) {
    this.gadgetUri = gadgetUri;
    this.serviceName = serviceName;
    this.user = user;
    this.scope = scope;
    this.allowModuleOverrides = allowModuleOverrides;
    this.globalRedirectUri = globalRedirectUri;
    if (store != null) {
      this.state = new OAuth2CallbackState(store.getStateCrypter());
    } else {
      this.state = new OAuth2CallbackState();
    }
    this.state.setGadgetUri(gadgetUri);
    this.state.setServiceName(serviceName);
    this.state.setUser(user);
    this.state.setScope(scope);
    this.state.setAppId(appId);
    this.authority = authority;
    this.contextRoot = contextRoot;
    this.errorResponse = false;
    this.redirecting = false;
    this.additionalRequestParams = Maps.newHashMap();
  }

  @Override
  public OAuth2Token getAccessToken() {
    return this.accessToken;
  }

  @Override
  public String getAuthorizationUrl() {
    return this.authorizationUrl;
  }

  @Override
  public String getClientAuthenticationType() {
    return this.clientAuthenticationType;
  }

  @Override
  public String getClientId() {
    return this.clientId;
  }

  @Override
  public byte[] getClientSecret() {
    return this.clientSecret;
  }

  @Override
  public OAuth2Error getError() {
    return this.error;
  }

  @Override
  public String getErrorContextMessage() {
    return this.errorContextMessage;
  }

  @Override
  public Throwable getErrorException() {
    return this.errorException;
  }

  @Override
  public String getErrorUri() {
    return this.errorUri;
  }

  @Override
  public String getGadgetUri() {
    return this.gadgetUri;
  }

  @Override
  public String getGrantType() {
    return this.grantType;
  }

  @Override
  public String getRedirectUri() {
    if (this.redirectUri == null || this.redirectUri.length() == 0) {
      String redirectUri2 = this.globalRedirectUri;
      if (this.authority != null) {
        redirectUri2 =
          redirectUri2.replace("%authority%", this.authority.getAuthority());
        redirectUri2 = redirectUri2.replace("%contextRoot%", this.contextRoot);
        redirectUri2 =
          redirectUri2.replace("%origin%", this.authority.getOrigin());
      }

      this.redirectUri = redirectUri2;
    }

    return this.redirectUri;
  }

  @Override
  public OAuth2Token getRefreshToken() {
    return this.refreshToken;
  }

  @Override
  public Map<String, String> getAdditionalRequestParams() {
    return this.additionalRequestParams;
  }

  @Override
  public String getScope() {
    return this.scope;
  }

  @Override
  public String getServiceName() {
    return this.serviceName;
  }

  @Override
  public OAuth2CallbackState getState() {
    if (this.state == null) {
      return new OAuth2CallbackState(null);
    }
    return this.state;
  }

  @Override
  public String getTokenUrl() {
    return this.tokenUrl;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public String getUser() {
    return this.user;
  }

  @Override
  public void invalidate() {
    this.accessToken = null;
    this.authorizationUrl = null;
    this.clientAuthenticationType = null;
    this.clientId = null;
    this.clientSecret = null;
    this.grantType = null;
    this.redirectUri = null;
    this.refreshToken = null;
    this.tokenUrl = null;
    this.type = null;
    this.errorResponse = false;
    this.redirecting = false;
    this.errorException = null;
  }

  @Override
  public boolean isAllowModuleOverrides() {
    return this.allowModuleOverrides;
  }

  @Override
  public boolean isAuthorizationHeader() {
    return this.authorizationHeader;
  }

  @Override
  public boolean isErrorResponse() {
    return this.errorResponse;
  }

  @Override
  public boolean isRedirecting() {
    return this.redirecting;
  }

  @Override
  public boolean isUrlParameter() {
    return this.urlParameter;
  }

  @Override
  public boolean isValid() {
    return this.grantType != null;
  }

  @Override
  public void setAccessToken(final OAuth2Token accessToken) {
    this.accessToken = accessToken;
  }

  public void setAuthorizationHeader(final boolean authorizationHeader) {
    this.authorizationHeader = authorizationHeader;
  }

  @Override
  public void setAuthorizationUrl(final String authorizationUrl) {
    this.authorizationUrl = authorizationUrl;
  }

  public void setClientAuthenticationType(final String clientAuthenticationType) {
    this.clientAuthenticationType = clientAuthenticationType;
  }

  public void setClientId(final String clientId) {
    this.clientId = clientId;
  }

  public void setClientSecret(final byte[] clientSecret) {
    this.clientSecret = clientSecret;
  }

  @Override
  public void setErrorResponse(final Throwable exception,
      final OAuth2Error error, final String contextMessage,
      final String errorUri) {
    this.errorResponse = true;
    this.errorException = exception;
    if (error != null) {
      this.error = error;
      this.errorContextMessage = contextMessage;
      this.errorUri = errorUri;
    }
  }

  public void setErrorUri(final String errorUri) {
    this.errorUri = errorUri;
  }

  public void setGrantType(final String grantType) {
    this.grantType = grantType;
  }

  @Override
  public void setRedirecting(final boolean redirecting) {
    this.redirecting = redirecting;
  }

  public void setRedirectUri(final String redirectUri) {
    this.redirectUri = redirectUri;
  }

  @Override
  public void setRefreshToken(final OAuth2Token refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public void setAdditionalRequestParams(
      final Map<String, String> additionalRequestParams) {
    this.additionalRequestParams = additionalRequestParams;
  }

  @Override
  public void setTokenUrl(final String tokenUrl) {
    this.tokenUrl = tokenUrl;
  }

  public void setType(final Type type) {
    this.type = type;
  }

  public void setUrlParameter(final boolean urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public void setAllowedDomains(final String[] allowedDomains) {
    this.allowedDomains = allowedDomains;
  }

  @Override
  public String[] getAllowedDomains() {
    return this.allowedDomains;
  }
}