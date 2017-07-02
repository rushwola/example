package com.rulai.framework.sso.client.validation;


import javax.servlet.FilterConfig;

/**
 * Implementation of TicketValidationFilter that can instanciate a SAML 1.1 Ticket Validator.
 * <p>
 * Deployers can provide the "casServerUrlPrefix" and "tolerance" properties of the Saml11TicketValidator via the
 * context or filter init parameters.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class Saml11TicketValidationFilter extends AbstractTicketValidationFilter {

    public Saml11TicketValidationFilter() {
        setArtifactParameterName("SAMLart");
        setServiceParameterName("TARGET");
    }

    protected TicketValidator getTicketValidator(final FilterConfig filterConfig) {
        final Saml11TicketValidator validator = new Saml11TicketValidator(getPropertyFromInitParams(filterConfig, "casServerUrlPrefix", null));
        final String tolerance = getPropertyFromInitParams(filterConfig, "tolerance", "1000");
        validator.setTolerance(Long.parseLong(tolerance));
        return validator;
    }
}
