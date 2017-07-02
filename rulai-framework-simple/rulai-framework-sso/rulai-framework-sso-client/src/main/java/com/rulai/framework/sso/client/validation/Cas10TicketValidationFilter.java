package com.rulai.framework.sso.client.validation;


import javax.servlet.FilterConfig;

/**
 * Implementation of AbstractTicketValidatorFilter that instanciates a Cas10TicketValidator.
 * <p>Deployers can provide the "casServerPrefix" and the "renew" attributes via the standard context or filter init
 * parameters.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class Cas10TicketValidationFilter extends AbstractTicketValidationFilter {

    protected TicketValidator getTicketValidator(final FilterConfig filterConfig) {
        final String casUrlServerPrefix = getPropertyFromInitParams(filterConfig, "casUrlServerPrefix", null);
        final Cas10TicketValidator validator = new Cas10TicketValidator(casUrlServerPrefix);
        validator.setRenew(Boolean.parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));

        return validator;
    }
}
