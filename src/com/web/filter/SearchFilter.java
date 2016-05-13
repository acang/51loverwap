package com.web.filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.http.*;
import java.util.*;

public class SearchFilter implements javax.servlet.Filter {


    protected FilterConfig filterConfig = null;



    public void setFilterConfig(FilterConfig config) {
        this.filterConfig = config;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {




              HttpServletRequest request1 = (HttpServletRequest) request;
              HttpServletResponse response1 = (HttpServletResponse) response;

               String uri = request1.getRequestURI();
                System.out.println(uri);
               String head = "";
               String head1 = "searchjgdlyx01-";
               String head2 = "searchjgzpyx01-";
               String head3 = "searchjg-";
               String head4 = "searchjgdlyx02-";
               String head5 = "searchjgzpyx02-";
               String head6 = "searchjg01-";
               String head7 = "searchjg02-";
               String head8 = "searchjgdlyx-";
               String head9 = "searchjgzpyx-";
               

               int index = -1;
               int index1 = uri.indexOf(head1);
               if(index1 > -1)
               {
                  head = head1;
                  index = index1;
               }
               int  index2 = uri.indexOf(head2);
               if(index2  > -1)
               {
                  head = head2;
                  index = index2;
               }
                int index3 = uri.indexOf(head3);

               if(index3 > -1)
               {
                  head = head3;
                  index = index3;
               }
               int index4 = uri.indexOf(head4);
               
               if(index4>-1)
               {
                   head = head4;
                   index = index4;
               }
               
               int index5 =uri.indexOf(head5);
               if(index5 >-1)
               {
                   head = head5;
                   index = index5;
               }
               
               int index6 =uri.indexOf(head6);
               if(index6 >-1)
               {
                   head = head6;
                   index = index6;
               }
               
               int index7 =uri.indexOf(head7);
               if(index7 >-1)
               {
                   head = head7;
                   index = index7;
               }
               
               int index8 =uri.indexOf(head8);
               if(index8 >-1)
               {
                   head = head8;
                   index = index8;
               }

               int index9 =uri.indexOf(head9);
               if(index9 >-1)
               {
                   head = head9;
                   index = index9;
               }


               if(index > -1)
               {
                 System.out.println("Ω¯»Î≈–∂œ------------");
                 uri = java.net.URLDecoder.decode(uri, "UTF-8");
                 uri = uri.substring(index + head.length(),uri.length()-5);
                 String[] array = uri.split("-");

                 HashMap hm = new HashMap();
                 String newquery = "?";
                 for(int i =0;i < array.length/2;i ++)
                 {
                   hm.put(array[i*2],array[i*2+1]);
                   newquery = newquery + array[i*2]+"="+array[i*2+1]+"&";
                 }
                 request1.setAttribute("query",hm);
                 String newUrl =head.substring(0,head.length()-1)+".jsp"+newquery;
                 request1.getRequestDispatcher(newUrl).forward(request1,response1);
                 System.out.println(newUrl);
                 return;
               }

            chain.doFilter(request, response);




    }

    public void destroy() {

        this.filterConfig = null;
    }

}
