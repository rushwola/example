package com.rulai.framework.sso.client.proxy;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulai.framework.sso.client.util.CommonUtils;
import com.rulai.framework.sso.client.util.XmlUtils;

/**
 * Implementation of a ProxyRetriever that follows the CAS 2.0 specification.
 * For more information on the CAS 2.0 specification, please see the <a
 * href="http://www.ja-sig.org/products/cas/overview/protocol/index.html">specification
 * document</a>.
 * <p/>
 * In general, this class will make a call to the CAS server with some specified
 * parameters and receive an XML response to parse.
 *
 * @author Scott Battaglia
 * @version $Revision: 11729 $ $Date: 2006-09-26 14:22:30 -0400 (Tue, 26 Sep 2006) $
 * @since 3.0
 */
public final class Cas20ProxyRetriever implements ProxyRetriever {

    /**
     * Instance of Commons Logging.
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Url to CAS server.
     */
    private final String casServerUrl;

    /**
     * Main Constructor.
     *
     * @param casServerUrl the URL to the CAS server (i.e. http://localhost/cas/)
     */
    public Cas20ProxyRetriever(final String casServerUrl) {
        CommonUtils.assertNotNull(casServerUrl,
                "casServerUrl cannot be null.");
        this.casServerUrl = casServerUrl;
    }

    public String getProxyTicketIdFor(final String proxyGrantingTicketId,
                                      final String targetService) {

        final String url = constructUrl(proxyGrantingTicketId, targetService);
        HttpURLConnection conn = null;
        try {
            final URL constructedUrl = new URL(url);
            conn = (HttpURLConnection) constructedUrl.openConnection();

            final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            final StringBuffer stringBuffer = new StringBuffer(255);
            final String response;

            synchronized (stringBuffer) {
                while ((line = in.readLine()) != null) {
                    stringBuffer.append(line);
                }
                response = stringBuffer.toString();
            }

            final String error = XmlUtils.getTextForElement(response,
                    "proxyFailure");

            if (CommonUtils.isNotEmpty(error)) {
                log.debug(error);
                return null;
            }

            return XmlUtils.getTextForElement(response, "proxyTicket");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private String constructUrl(final String proxyGrantingTicketId,
                                final String targetService) {
        try {
            return this.casServerUrl + "proxy" + "?pgt="
                    + proxyGrantingTicketId + "&targetService="
                    + URLEncoder.encode(targetService, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
