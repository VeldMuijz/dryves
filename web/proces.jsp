package Dryves;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.*" %>
<%! 

%>
<%@page import="Dryves.Login"%>
<%@page import="Dryves.Lid"%>
<jsp:useBean id="formHandler" class="Dryves.Lid" scope="request">
<jsp:setProperty name="formHandler" property="*"/>
</jsp:useBean>
<% 
   if (formHandler.validate()) {
%>
<jsp:forward page="success.jsp"/>
<%
   }  else {
%>
<jsp:forward page="retry.jsp"/>
<%
   }
%>

