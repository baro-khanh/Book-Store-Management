/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author buido
 */
public class DispatcherFilter implements Filter {

    private static final boolean debug = true;
    private static final String LOGIN = "login.jsp";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private final List<String> guest;
    private final List<String> admin;
    private final List<String> user;

    public DispatcherFilter() {
        guest = new ArrayList<>();
        guest.add("login.jsp");
        guest.add("list_book.jsp");
        guest.add("error.jsp");
        guest.add("LoginServlet");
        guest.add("SearchBookServlet");
        guest.add("detail_book.jsp");
        guest.add("LoadBookSeverlet");
        guest.add("LogoutServlet");

        admin = new ArrayList<>();
        admin.add("login.jsp");
        admin.add("list_book.jsp");
        admin.add("error.jsp");
        admin.add("insert_book.jsp");
        admin.add("LoginServlet");
        admin.add("SearchBookServlet");
        admin.add("DeleteServlet");
        admin.add("InsertServlet");
        admin.add("EditServlet");
        admin.add("detail_book.jsp");
        admin.add("update_book.jsp");
        admin.add("UpdateServlet");
        admin.add("insert_user.jsp");
        admin.add("InsertUserServlet");
        admin.add("SearchUserServlet");
        admin.add("list_user.jsp");
        admin.add("insert_discount.jsp");
        admin.add("InsertDiscountServlet");
        admin.add("SearchDiscountServlet");
        admin.add("list_discount.jsp");
        admin.add("LogoutServlet");
        admin.add("LoadBookSeverlet");
        admin.add("SearchOrderServlet");
        admin.add("view_order.jsp");
        admin.add("detail_order.jsp");
        admin.add("SearchDetailOrderServlet");

        user = new ArrayList<>();
        user.add("login.jsp");
        user.add("list_book.jsp");
        user.add("error.jsp");
        user.add("LoginServlet");
        user.add("SearchBookServlet");
        user.add("EditServlet");
        user.add("detail_book.jsp");
        user.add("AddCartServlet");
        user.add("view_cart.jsp");
        user.add("DeleteCartDetailServlet");
        user.add("UpdateCartDetailServlet");
        user.add("SearchUserServlet");
        user.add("confirm_cart.jsp");
        user.add("ConfirmServlet");
        user.add("SearchOrderServlet");
        user.add("view_order.jsp");
        user.add("SearchDetailOrderServlet");
        user.add("detail_order.jsp");
        user.add("LogoutServlet");
        user.add("CheckSaleCodeServlet");
        user.add("LoadBookSeverlet");

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("ROLE");
        String uri = req.getRequestURI();
        String url = LOGIN;
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex + 1);  //đích đến

        HttpServletResponse res = (HttpServletResponse) response;
        if (uri.contains("img")) {
            chain.doFilter(request, response);
        } else {
            if (resource.length() > 0) {
                if (session.getAttribute("USER") == null) {
                    //chua dang nhap
                    if (guest.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        req.setAttribute("ERROR", "Page not found");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                    //dang nhap roi
                    if (role.equals("admin")) {
                        if (admin.contains(resource)) {
                            chain.doFilter(request, response);
                        } else {
                            res.sendRedirect("login.jsp");
                        }
                    } else if (role.equals("user")) {
                        if (user.contains(resource)) {
                            chain.doFilter(request, response);
                        } else {
                            res.sendRedirect("login.jsp");
                        }
                    } else {
                        req.setAttribute("ERROR", "Your role is no support");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            } else {
                res.sendRedirect("login.jsp");
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DispatcherFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatcherFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatcherFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
