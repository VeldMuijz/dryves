<%-- 
    Document   : navigation
    Created on : 5-apr-2013, 13:21:16
    Author     : Vincent
--%>

<%@page import="Dryves.Lid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<div class="navigation">

<!--    check of er een ingelogde gebruiker is, zo ja dan toon je dit menu zonder registratie en login:-->
    <c:choose>
        <c:when test="${currentSessionUser.lidnr > 0}"> 
            <button onclick="window.location = 'index.jsp';" <% if (request.getParameter("menu_active").equals("home")) {%> class="active" <% }%> >Home</button><button onclick="window.location = 'watisdryves.jsp';" <% if (request.getParameter("menu_active").equals("watisdryves")) {%> class="active" <% }%> >Wat is dryves</button><button onclick="window.location = 'faq.jsp';" <% if (request.getParameter("menu_active").equals("faq")) {%> class="active" <% }%> >FAQ</button><button onclick="window.location = '/Dryves/MijnDryves';" <% if (request.getParameter("menu_active").equals("mijndryves")) {%> class="active" <% }%> >Mijn Dryves</button>
        </c:when>
        <c:otherwise>
            <button onclick="window.location = 'index.jsp';" <% if (request.getParameter("menu_active").equals("home")) {%> class="active" <% }%> >Home</button><button onclick="window.location = 'watisdryves.jsp';" <% if (request.getParameter("menu_active").equals("watisdryves")) {%> class="active" <% }%> >Wat is dryves</button><button onclick="window.location = 'faq.jsp';" <% if (request.getParameter("menu_active").equals("faq")) {%> class="active" <% }%> >FAQ</button><button onclick="window.location = 'registratie.jsp';" <% if (request.getParameter("menu_active").equals("registratie")) {%> class="active" <% }%> >Registreer</button><button  class="loginButton" onclick="window.location = 'login.jsp';">Login</button>
        </c:otherwise>
    </c:choose>

    <c:if test="${currentSessionUser.lidnr > 0}"> 
        <div class="welkom" id="welkom" style=" float: right; width: 300px; height: 100%;">

            <div style="float: left; margin-right: 5px;">Welkom, <br/>
                <div id="anaam"> <label id="vnaam"></label></div>
                <c:out value="${currentSessionUser.vnaam}"/> <c:out value="${currentSessionUser.anaam}"/></div> 

            <!--					TODO Hier nog de actuele aantal ongelezen berichten ophalen-->
            <div style="float: right; margin-right: 5px;"> 
                <a href="mijnberichten.jsp"> 0</a> :nieuwe berichten  <br/> 
                <a href="Uitloggen" style="float: right;"> uitloggen </a>
            </div>

        </div>
    </c:if>

</div>


