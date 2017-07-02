package com.rulai.framework.sso.client.util;


import com.rulai.framework.sso.client.validation.Assertion;

/**
 * Static holder that places Assertion in a threadlocal.
 *
 * @author Scott Battaglia
 * @version $Revision: 11728 $ $Date: 2006-09-26 14:20:43 -0400 (Tue, 26 Sep 2006) $
 * @since 3.0
 */
public class AssertionHolder {

    /**
     * ThreadLocal to hold the Assertion for Threads to access.
     */
    private static final ThreadLocal threadLocal = new ThreadLocal();


    /**
     * Retrieve the assertion from the ThreadLocal.
     */
    public static Assertion getAssertion() {
        return (Assertion) threadLocal.get();
    }

    /**
     * Add the Assertion to the ThreadLocal.
     */
    public static void setAssertion(final Assertion assertion) {
        threadLocal.set(assertion);
    }

    /**
     * Clear the ThreadLocal.
     */
    public static void clear() {
        threadLocal.set(null);
    }
}
