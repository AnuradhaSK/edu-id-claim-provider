package org.wso2.carbon.edu.id.claim.provider;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.application.common.model.ClaimMapping;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;
import org.wso2.carbon.identity.oauth2.authz.OAuthAuthzReqMessageContext;
import org.wso2.carbon.identity.oauth2.dto.OAuth2AccessTokenRespDTO;
import org.wso2.carbon.identity.oauth2.dto.OAuth2AuthorizeRespDTO;
import org.wso2.carbon.identity.oauth2.token.OAuthTokenReqMessageContext;
import org.wso2.carbon.identity.oauth2.token.handlers.claims.JWTAccessTokenClaimProvider;
import org.wso2.carbon.identity.openidconnect.ClaimProvider;

import java.util.HashMap;
import java.util.Map;

public class EDUIDClaimProvider implements ClaimProvider, JWTAccessTokenClaimProvider {

    private static final String EDU_ID = "eduID";
    private static final String EMAIL = "email";

    @Override
    public Map<String, Object> getAdditionalClaims(OAuthAuthzReqMessageContext oAuthAuthzReqMessageContext)
            throws IdentityOAuth2Exception {

        return null;
    }

    /**
     * Add additional claims to JWT access token.
     *
     * @param oAuthTokenReqMessageContext Oauth2 token request message's context data.
     * @return Map of additional claims to be added to JWT access token.
     * @throws IdentityOAuth2Exception IdentityOAuth2Exception.
     */
    @Override
    public Map<String, Object> getAdditionalClaims(OAuthTokenReqMessageContext oAuthTokenReqMessageContext)
            throws IdentityOAuth2Exception {

        Map<String, Object> additionalClaims = new HashMap<>();
        String email = null;
        Map<ClaimMapping, String> userAttributes = oAuthTokenReqMessageContext.getAuthorizedUser().getUserAttributes();
        for (Map.Entry<ClaimMapping, String> entry : userAttributes.entrySet()) {
            if (entry.getKey().getLocalClaim().getClaimUri().equals(EMAIL)) {
                email = entry.getValue();
                break;
            }
        }
        if (StringUtils.isNotBlank(email)) {
            additionalClaims.put(EDU_ID, email + "+1234567890");
        }
        return additionalClaims;
    }

    @Override
    public Map<String, Object> getAdditionalClaims(OAuthAuthzReqMessageContext oAuthAuthzReqMessageContext,
                                                   OAuth2AuthorizeRespDTO oAuth2AuthorizeRespDTO)
            throws IdentityOAuth2Exception {

        return null;
    }

    /**
     * Add additional claims to ID token.
     *
     * @param oAuthTokenReqMessageContext Oauth2 token request message's context data.
     * @param oAuth2AccessTokenRespDTO Oauth2 access token response data transfer object.
     * @return Map of additional claims to be added to ID token.
     * @throws IdentityOAuth2Exception IdentityOAuth2Exception.
     */
    @Override
    public Map<String, Object> getAdditionalClaims(OAuthTokenReqMessageContext oAuthTokenReqMessageContext,
                                                   OAuth2AccessTokenRespDTO oAuth2AccessTokenRespDTO)
            throws IdentityOAuth2Exception {

        Map<String, Object> additionalClaims = new HashMap<>();
        String email = null;
        Map<ClaimMapping, String> userAttributes = oAuthTokenReqMessageContext.getAuthorizedUser().getUserAttributes();
        for (Map.Entry<ClaimMapping, String> entry : userAttributes.entrySet()) {
            if (entry.getKey().getLocalClaim().getClaimUri().equals(EMAIL)) {
                email = entry.getValue();
                break;
            }
        }
        if (StringUtils.isNotBlank(email)) {
            additionalClaims.put(EDU_ID, email + "+1234567890");
        }
        return additionalClaims;
    }
}
