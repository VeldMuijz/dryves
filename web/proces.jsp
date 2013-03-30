<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.*" %>
<%! 

%>
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

