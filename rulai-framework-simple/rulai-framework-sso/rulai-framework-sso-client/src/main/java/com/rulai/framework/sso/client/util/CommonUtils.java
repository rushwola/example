package com.rulai.framework.sso.client.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common utilities so that we don't need to include Commons Lang.
 *
 * @author Scott Battaglia
 * @version $Revision: 11729 $ $Date: 2006-09-26 14:22:30 -0400 (Tue, 26 Sep 2006) $
 * @since 3.0
 */
public final class CommonUtils {

    /** Instance of Commons Logging. */
    private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

    private CommonUtils() {
        // nothing to do
    }

    /**
     * Check whether the object is null or not. If it is, throw an exception and
     * display the message.
     *
     * @param object  the object to check.
     * @param message the message to display if the object is null.
     */
    public static void assertNotNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Check whether the collection is null or empty. If it is, throw an
     * exception and display the message.
     *
     * @param c       the collecion to check.
     * @param message the message to display if the object is null.
     */
    public static void assertNotEmpty(final Collection c, final String message) {
        assertNotNull(c, message);
        if (c.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the statement is true, otherwise throw an exception with the
     * provided message.
     *
     * @param cond    the codition to assert is true.
     * @param message the message to display if the condition is not true.
     */
    public static void assertTrue(final boolean cond, final String message) {
        if (!cond) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Determines whether the String is null or of length 0.
     *
     * @param string the string to check
     * @return true if its null or length of 0, false otherwise.
     */
    public static boolean isEmpty(final String string) {
        return string == null || string.length() == 0;
    }

    /**
     * Determines if the String is not empty. A string is not empty if it is not
     * null and has a length > 0.
     *
     * @param string the string to check
     * @return true if it is not empty, false otherwise.
     */
    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    /**
     * Determines if a String is blank or not. A String is blank if its empty or
     * if it only contains spaces.
     *
     * @param string the string to check
     * @return true if its blank, false otherwise.
     */
    public static boolean isBlank(final String string) {
        return isEmpty(string) || string.trim().length() == 0;
    }

    /**
     * Determines if a string is not blank. A string is not blank if it contains
     * at least one non-whitespace character.
     *
     * @param string the string to check.
     * @return true if its not blank, false otherwise.
     */
    public static boolean isNotBlank(final String string) {
        return !isBlank(string);
    }

    /**
     * Constructs the URL to use to redirect to the CAS server.
     *
     * @param casServerLoginUrl the CAS Server login url.
     * @param serviceParameterName the name of the parameter that defines the service.
     * @param serviceUrl the actual service's url.
     * @param renew whether we should send renew or not.
     * @param gateway where we should send gateway or not.
     * @return the fully constructed redirect url.
     */
    public static final String constructRedirectUrl(final String casServerLoginUrl, final String serviceParameterName, final String serviceUrl, final boolean renew, final boolean gateway) {
        try {
        return casServerLoginUrl + "?" + serviceParameterName + "="
                    + URLEncoder.encode(serviceUrl, "UTF-8")
                    + (renew ? "&renew=true" : "")
                    + (gateway ? "&gateway=true" : "");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

/**
     * Constructs a service url from the HttpServletRequest or from the given
     * serviceUrl. Prefers the serviceUrl provided if both a serviceUrl and a
     * serviceName.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @return the service url to use.
     */
    public static final String constructServiceUrl(final HttpServletRequest request,
                                               final HttpServletResponse response, final String service, final String serverName, final String artifactParameterName) {
        if (CommonUtils.isNotBlank(service)) {
            return response.encodeURL(service);
        }

        final StringBuffer buffer = new StringBuffer();

        synchronized (buffer) {
            if (!serverName.startsWith("https://") && !serverName.startsWith("http://")) {
                buffer.append(request.isSecure() ? "https://" : "http://");
            }

            buffer.append(serverName);
            buffer.append(request.getRequestURI());

            if (CommonUtils.isNotBlank(request.getQueryString())) {
                final int location = request.getQueryString().indexOf(
                        artifactParameterName + "=");

                if (location == 0) {
                    final String returnValue = response.encodeURL(buffer
                            .toString());
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("serviceUrl generated: " + returnValue);
                    }
                    return returnValue;
                }

                buffer.append("?");

                if (location == -1) {
                    buffer.append(request.getQueryString());
                } else if (location > 0) {
                    final int actualLocation = request.getQueryString()
                            .indexOf("&" + artifactParameterName + "=");

                    if (actualLocation == -1) {
                        buffer.append(request.getQueryString());
                    } else if (actualLocation > 0) {
                        buffer.append(request.getQueryString().substring(0,
                                actualLocation));
                    }
                }
            }
        }

        final String returnValue = response.encodeURL(buffer.toString());
        if (LOG.isDebugEnabled()) {
            LOG.debug("serviceUrl generated: " + returnValue);
        }
        return returnValue;
    }

}
