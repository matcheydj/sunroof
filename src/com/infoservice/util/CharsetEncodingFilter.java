package com.infoservice.util;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
/**
 * 
 * @author sunzj
 *
 */
public class CharsetEncodingFilter extends HttpServlet implements Filter {
    
      /**
     * The default character encoding to set for requests that pass through this
     * filter.
     */
    protected String encoding = null;
    /**
     * The filter configuration object we are associated with. If this value is
     * null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;
    /**
     * Should a character encoding specified by the client be ignored?
     */
    protected boolean ignore = true;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (ignore || (request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(selectEncoding(request));
        }
        chain.doFilter(request, response);
    }

    /**
     * Place this filter into service.
     *
     * @param filterConfig
     *            The filter configuration object
     */
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        this.ignore = value == null || value.equalsIgnoreCase("true")
                || value.equalsIgnoreCase("yes");
    }

    /**
     * Select an appropriate character encoding to be used, based on the
     * characteristics of the current request and/or filter initialization
     * parameters. If no character encoding should be set, return
     * <code>null</code>.
     * <p>
     * The default implementation unconditionally returns the value configured
     * by the <strong>encoding</strong> initialization parameter for this
     * filter.
     *
     * @param request
     *            The servlet request we are processing
     */
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

    /**
     * Returns the filterConfig.
     *
     * @return FilterConfig
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    /**
     * Sets the filterConfig.
     *
     * @param filterConfig
     *            The filterConfig to set
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
 }
