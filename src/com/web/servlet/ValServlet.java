package com.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class ValServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            String checkcode=request.getParameter("checkcode");
            if(checkcode==null||checkcode.length()==0||checkcode==""){
            	out.println("<script language='javascript'>{alert('您输入的验证码为空，请重新输入！');}</script>");
            }else{
                if(checkcode.equals(request.getSession().getAttribute("rand"))){
                	out.println("<script language='javascript'>{sendSms();}</script>");
                }else{
                	out.println("<script language='javascript'>{alert('您输入的验证码有误，请重新输入！');}</script>");
                }
            }
        }catch(Exception e){
             e.printStackTrace();
        }
    }
}
