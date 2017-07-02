package com.rulai.framework.sso.client.authentication;


import java.util.Collections;
import java.util.Map;

import com.rulai.framework.sso.client.proxy.ProxyRetriever;
import com.rulai.framework.sso.client.util.CommonUtils;

/**
 * Concrete implementation of the AttributePrincipal interface.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public class AttributePrincipalImpl implements AttributePrincipal{

    /** The unique identifier for this principal. */
    private final String name;

    /** Map of key/value pairs about this principal. */
    private final Map attributes;

    /** The CAS 2 ticket used to retrieve a proxy ticket. */
    private final String proxyGrantingTicket;

    /** The method to retrieve a proxy ticket from a CAS server. */
    private final ProxyRetriever proxyRetriever;

    /**
     * Constructs a new principal with an empty map of attributes.
     *
     * @param name the unique identifier for the principal.
     */
    public AttributePrincipalImpl(final String name) {
        this(name, Collections.EMPTY_MAP);
    }

    /**
     * Constructs a new principal with the supplied name and attributes.
     *
     * @param name the unique identifier for the principal.
     * @param attributes the key/value pairs for this principal.
     */
    public AttributePrincipalImpl(final String name, final Map attributes) {
        this(name, attributes,  null, null);
    }

    /**
     * Constructs a new principal with the supplied name and the proxying capabilities.
     *
     * @param name the unique identifier for the principal.
     * @param proxyGrantingTicket the ticket associated with this principal.
     * @param proxyRetriever the ProxyRetriever implementation to call back to the CAS server.
     */
    public AttributePrincipalImpl(final String name, final String proxyGrantingTicket, final ProxyRetriever proxyRetriever) {
        this(name, Collections.EMPTY_MAP, proxyGrantingTicket, proxyRetriever);
    }

    /**
     * Constructs a new principal witht he supplied name, attributes, and proxying capabilities.
     *
     * @param name the unique identifier for the principal.
     * @param attributes the key/value pairs for this principal.
     * @param proxyGrantingTicket the ticket associated with this principal.
     * @param proxyRetriever the ProxyRetriever implementation to call back to the CAS server.
     */
    public AttributePrincipalImpl(final String name, final Map attributes, final String proxyGrantingTicket, final ProxyRetriever proxyRetriever) {
        this.name = name;
        this.attributes = attributes;
        this.proxyGrantingTicket = proxyGrantingTicket;
        this.proxyRetriever = proxyRetriever;

        CommonUtils.assertNotNull(this.name, "name cannot be null.");
        CommonUtils.assertNotNull(this.attributes, "attributes cannot be null.");
    }

    public Map getAttributes() {
        return this.attributes;
    }

    public String getProxyTicketFor(String service) {
        if (proxyGrantingTicket != null) {
            return this.proxyRetriever.getProxyTicketIdFor(this.proxyGrantingTicket, service);
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}
