<%-- 
    Document   : nieuwbericht
    Created on : 16-mei-2013, 21:12:34
    Author     : H
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        <script type="text/javascript">
            (function(d) {
                d.fn.extend({accordion: function() {
                        return this.each(function() {
                            var $ul = $(this);
                            if ($ul.data('accordiated'))
                                return false;
                            $.each($ul.find('ul, li>div'), function() {
                                $(this).data('accordiated', true);
                                $(this).hide()
                            });
                            $.each($ul.find('a'), function() {
                                $(this).click(function(e) {
                                    activate(this);
                                    return void(0)
                                })
                            });
                            var c = (location.hash) ? $ul.find('a[href=' + location.hash + ']')[0] : '';
                            if (c) {
                                activate(c, 'toggle');
                                $(c).parents().show()
                            }
                            function activate(a, b) {
                                $(a).parent('li').toggleClass('active').siblings().removeClass('active').children('ul, div').slideUp('fast');
                                $(a).siblings('ul, div')[(b || 'slideToggle')]((!b) ? 'fast' : null)
                            }}
                        )
                    }})
            })(jQuery);

            (function(jQuery) {
                jQuery.fn.extend({
                    accordion: function() {
                        return this.each(function() {

                            var $ul = $(this);

                            if ($ul.data('accordiated'))
                                return false;

                            $.each($ul.find('ul, li>div'), function() {
                                $(this).data('accordiated', true);
                                $(this).hide();
                            });

                            $.each($ul.find('a'), function() {
                                $(this).click(function(e) {
                                    activate(this);
                                    return void(0);
                                });
                            });

                            var active = (location.hash) ? $(this).find('a[href=' + location.hash + ']')[0] : '';

                            if (active) {
                                activate(active, 'toggle');
                                $(active).parents().show();
                            }

                            function activate(el, effect) {
                                $(el).parent('li').toggleClass('active').siblings().removeClass('active').children('ul, div').slideUp('fast');
                                $(el).siblings('ul, div')[(effect || 'slideToggle')]((!effect) ? 'fast' : null);
                            }

                        });
                    }
                });
            })(jQuery);


        </script>

        <style>

            .pageContent { width: 400px; }
            .accordion { list-style-type: none; padding: 0; margin: 0 0 30px; border: 1px solid #17a; border-top: none; border-left: none; }
            .accordion ul { padding: 0; margin: 0; float: left; display: block; width: 100%; }
            .accordion li { background: #3cf; cursor: pointer; list-style-type: none; padding: 0; margin: 0; float: left; display: block; width: 100%;}
            .accordion li.active>a { background: url('close.gif') no-repeat center right; }
            .accordion li div { padding: 20px; background: #aef; display: block; clear: both; float: left; width: 360px;}
            .accordion a { text-decoration: none; border-bottom: 1px solid #4df; font: bold 1.1em/2em Arial, sans-serif; color: #222; padding: 0 10px; display: block; cursor: pointer; background: url('open.gif') no-repeat center right;}

            /* Level 2 */
            .accordion li ul li { background: #7FD2FF; font-size: 0.9em; }

        </style>


    </head>
    <body>

        <div class="background">

            <img src="images/background1.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>



            <jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="faq"></jsp:param>
            </jsp:include>

            <div class="contentPanel">




                <html lang="en">

                    <head>
                        <meta charset="utf-8" />
                        <title>Nieuw bericht</title>



                    <s
                        </head>

                        <body>




                            <%  
                          String ritnr=  request.getParameter("ritnr");
                          String lidnr=  request.getParameter("lidnr");
                          String datum=  request.getParameter("datum");
                            %>


                            <form action="VerstuurBericht" method="get">

                                Datum                             <br>

                                <input type="hidden" name="ritnr" value="<% out.print(ritnr);%>" />
                                <input type="hidden" name="lidnr" value="<% out.print(lidnr);%>" />
                                <input type="hidden" name="datum" value="<% out.print(datum);%>" />
                                

                                Uw Bericht                            <br>

                                <input type="text" name="inhoud"  />

                                <br>

                                <input type="submit" />


                            </form>


                            </div>
                        </body>