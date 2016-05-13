package com.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-7-12
 * Time: ÏÂÎç9:11
 * To change this template use File | Settings | File Templates.
 */
public class CodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       protected void service(HttpServletRequest requset, HttpServletResponse response)
                throws ServletException, IOException {
                String content = requset.getParameter("content");
                EncoderHandler encoder = new EncoderHandler();
                encoder.encoderQRCoder(content, response);
            }

}
