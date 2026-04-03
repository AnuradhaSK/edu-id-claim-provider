package org.wso2.carbon.edu.id.claim.provider.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.edu.id.claim.provider.EDUIDClaimProvider;
import org.wso2.carbon.identity.oauth2.token.handlers.claims.JWTAccessTokenClaimProvider;
import org.wso2.carbon.identity.openidconnect.ClaimProvider;

@Component(
        name = "identity.edu.id.claim.provider.component",
        immediate = true
)
public class ClaimProviderServiceComponent {

    private static final Log LOG = LogFactory.getLog(ClaimProviderServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {

        try {
            EDUIDClaimProvider eduidClaimProvider = new EDUIDClaimProvider();
            context.getBundleContext()
                    .registerService(ClaimProvider.class, eduidClaimProvider, null);
            context.getBundleContext()
                    .registerService(JWTAccessTokenClaimProvider.class, eduidClaimProvider, null);
        } catch (Exception e) {
            LOG.error("Error when registering EDUIDClaimProvider service.", e);
        }
        LOG.debug("EDUIDClaimProvider bundle is activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("EDUIDClaimProvider bundle is deactivated");
        }
    }
}
