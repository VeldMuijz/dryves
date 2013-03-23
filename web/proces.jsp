<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%! 
  
%>
<jsp:useBean id="formHandler" class="test.FormBean" scope="request">
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

