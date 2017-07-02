package com.rulai.framework.sso.client.validation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opensaml.SAMLAssertion;
import org.opensaml.SAMLAttribute;
import org.opensaml.SAMLAttributeStatement;
import org.opensaml.SAMLAuthenticationStatement;
import org.opensaml.SAMLException;
import org.opensaml.SAMLResponse;
import org.opensaml.SAMLStatement;
import org.opensaml.SAMLSubject;

import com.rulai.framework.sso.client.authentication.AttributePrincipal;
import com.rulai.framework.sso.client.authentication.AttributePrincipalImpl;

/**
 * TicketValidator that can understand validating a SAML artifact.  This includes the SOAP request/response.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class Saml11TicketValidator extends AbstractUrlBasedTicketValidator {

    /** Time tolerance to allow for time drifting. */
    private long tolerance = 1000L;

    public Saml11TicketValidator(final String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }

    protected String getUrlSuffix() {
        return "samlValidate";
    }

    protected void populateUrlAttributeMap(final Map urlParameters) {
        final String service = (String) urlParameters.get("service");
        urlParameters.remove("service");
        urlParameters.remove("ticket");
        urlParameters.put("TARGET", service);
    }

    protected Assertion parseResponseFromServer(final String response) throws TicketValidationException {
        try {
            final SAMLResponse samlResponse = new SAMLResponse(new ByteArrayInputStream(response.getBytes()));

            if (!samlResponse.getAssertions().hasNext()) {
                throw new TicketValidationException("No assertions found.");
            }

            for (final Iterator iter = samlResponse.getAssertions(); iter.hasNext();) {
                final SAMLAssertion assertion = (SAMLAssertion) iter.next();

                if (!isValidAssertion(assertion)) {
                    continue;
                }

                final SAMLAuthenticationStatement authenticationStatement = getSAMLAuthenticationStatement(assertion);

                if (authenticationStatement == null) {
                    throw new TicketValidationException("No AuthentiationStatement found in SAML Assertion.");
                }
                final SAMLSubject subject = authenticationStatement.getSubject();

                if (subject == null) {
                    throw new TicketValidationException("No Subject found in SAML Assertion.");
                }

                final SAMLAttribute[] attributes = getAttributesFor(assertion, subject);

                final Map personAttributes = new HashMap();

                for (int i = 0; i < attributes.length; i++) {
                    final SAMLAttribute samlAttribute = attributes[i];
                    final List values = getValuesFrom(samlAttribute);

                    personAttributes.put(samlAttribute.getName(), values.size() == 1 ? values.get(0) : values);
                }

                final AttributePrincipal principal = new AttributePrincipalImpl(subject.getNameIdentifier().getName(), personAttributes);


                final Map authenticationAttributes = new HashMap();
                authenticationAttributes.put("samlAuthenticationStatement::authMethod", authenticationStatement.getAuthMethod());

                final Assertion casAssertion = new AssertionImpl(principal, authenticationAttributes);
                return casAssertion;
            }
       } catch (final SAMLException e) {
            throw new TicketValidationException(e);
        }

        throw new TicketValidationException("No valid assertions from the SAML response found.");
    }

    private boolean isValidAssertion(final SAMLAssertion assertion) {
        final Date notBefore = assertion.getNotBefore();
        final Date notOnOrAfter = assertion.getNotOnOrAfter();

        if (assertion.getNotBefore() == null || assertion.getNotOnOrAfter() == null) {
            log.debug("Assertion has no bounding dates. Will not process.");
            return false;
        }

        final long currentTime = new Date().getTime();

        if (currentTime + tolerance < notBefore.getTime()) {
            log.debug("skipping assertion that's not yet valid...");
            return false;
        }

        if (notOnOrAfter.getTime() <= currentTime - tolerance) {
            log.debug("skipping expired assertion...");
            return false;
        }

        return true;
    }

    private SAMLAuthenticationStatement getSAMLAuthenticationStatement(final SAMLAssertion assertion) {
        for (final Iterator iter = assertion.getStatements(); iter.hasNext();) {
            final SAMLStatement statement = (SAMLStatement) iter.next();

            if (statement instanceof SAMLAuthenticationStatement) {
                return (SAMLAuthenticationStatement) statement;
            }
        }

        return null;
    }

    private SAMLAttribute[] getAttributesFor(final SAMLAssertion assertion, final SAMLSubject subject) {
        final List attributes = new ArrayList();
        for (final Iterator iter = assertion.getStatements(); iter.hasNext();) {
            final SAMLStatement statement = (SAMLStatement) iter.next();

            if (statement instanceof SAMLAttributeStatement) {
                final SAMLAttributeStatement attributeStatement = (SAMLAttributeStatement) statement;
                // used because SAMLSubject does not implement equals
                if (subject.getNameIdentifier().getName().equals(attributeStatement.getSubject().getNameIdentifier().getName())) {
                    for (final Iterator iter2 = attributeStatement.getAttributes(); iter2.hasNext();)
                    attributes.add(iter2.next());
                }
            }
        }

        return (SAMLAttribute[]) attributes.toArray(new SAMLAttribute[attributes.size()]);
    }

    private List getValuesFrom(final SAMLAttribute attribute) {
        final List list = new ArrayList();
        for (final Iterator iter = attribute.getValues(); iter.hasNext();) {
            list.add(iter.next());
        }

        return list;
    }

    protected String retrieveResponseFromServer(final URL validationUrl, final String ticket) {
        final String MESSAGE_TO_SEND = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body><samlp:Request xmlns:samlp=\"urn:oasis:names:tc:SAML:1.0:protocol\"  MajorVersion=\"1\" MinorVersion=\"1\" RequestID=\"_192.168.16.51.1024506224022\" IssueInstant=\"2002-06-19T17:03:44.022Z\">"
                + "<samlp:AssertionArtifact>" + ticket
                + "</samlp:AssertionArtifact></samlp:Request></SOAP-ENV:Body></SOAP-ENV:Envelope>";

        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) validationUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(MESSAGE_TO_SEND.length()));
            conn.setRequestProperty("SOAPAction", "http://www.oasis-open.org/committees/security");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            final DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(MESSAGE_TO_SEND);
            out.flush();
            out.close();

            final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer buffer = new StringBuffer(256);

            synchronized (buffer) {
                String line;

                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);       
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public void setTolerance(final long tolerance) {
        this.tolerance = tolerance;
    }
}
