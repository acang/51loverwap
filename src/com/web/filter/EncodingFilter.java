package com.web.filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.http.*;

public class EncodingFilter implements javax.servlet.Filter {

    protected String encoding = "GBK";
    protected FilterConfig filterConfig = null;

    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

    public void setFilterConfig(FilterConfig config) {
        this.filterConfig = config;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String encoding = selectEncoding(request);
        //System.out.println(encoding);
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession   session = request1.getSession();
//        String url = (String)request1.getSession().getAttribute("httpurl");
//        if(url == null)
//        {
//          url = request1.getHeader("referer");
//          session.setAttribute("httpurl",url);
//        }
        String ip = (String)session.getAttribute("IPLOG");
        if(ip == null)
        {
          ip = request1.getRemoteAddr();
          //220.161.212.10
          //222.77.187.107
          if(ip.equals("203.208.60.142") || ip.startsWith("124.115.4") || ip.startsWith("124.115.0") || ip.startsWith("124.115.1") || ip.startsWith("72.30.65.49") || ip.startsWith("123.125.66") || ip.startsWith("123.125.64") || ip.equals("202.96.138.101"))
          {
            System.out.println("¹¥»÷:"+ip+request1.getRequestURL()+":"+request1.getQueryString());
            ((HttpServletResponse)response).sendRedirect("http://www.sina.com");
            return;
          }

          session.setAttribute("IPLOG",ip);
        }





        if (encoding != null) {

            request.setCharacterEncoding(encoding);
            response.setContentType("text/html;charset=" + encoding);
        }
         chain.doFilter(request, response);




    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

}
