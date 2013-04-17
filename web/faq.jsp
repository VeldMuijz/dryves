<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


				<jsp:include page="navigatie.jsp"  flush="true" />

			<div class="contentPanel">

				<html lang="en">

					<head>
						<meta charset="utf-8" />
						<title>jQuery UI Accordion - Default functionality</title>
						<link rel="stylesheet"
							  href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
						<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
						<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
						<link rel="stylesheet" href="/resources/demos/style.css" />
						<script>
			$(function() {
				$("#menu").menu();
				$("#accordion").accordion();
			});
						</script>
					</head>

					<body>
						<div id="accordion">
							<h3>Hoe plan ik een rit?</h3>

							<div>

								<p>


									Een rit kan geplanned worden door op "Plan rit" te klikken

								</p>

							</div>
							<h3>Hoe zet ik een rit online?</h3>

							<div>
								<p>Vanuit de pagina MyDryves kun je de ritte die ingevoerd zijn publiceren.</p>
							</div>
							<h3>Hoe verwijder ik een rit?</h3>

							<div>
								<p>Vanuit de pagina MyDryves kun je ritten selecteren en vervolgens op het knopje "verwijder rit" 
									klikken om de rit te verwijderen.</p>
							</div>
							<h3>Hoe spreek ik een ontmoetingsplek af?</h3>
							<div>
								<p>Bij het aangaan van de rit kun je een persoonlijk bericht sturen naar de rit aanbieder
									om een plaats overeen te komen.</p>
							</div>
							<h3>Hoe voeg ik herhalende ritten toe?</h3>
							<div>
								<p>uitleg hoe dit te bewerkstelligen.</p>
							</div>
							<h3>Hoe verleng ik mijn herhalende ritten?</h3>
							<div>
								<p>uitleg hoe dit te bewerkstelligen.</p>
							</div>
							<h3>Er worden geen ritten getoond, hoe kan dit?</h3>
							<div>
								<p>uitleg hoe dit mogelijk is.</p>
							</div>
					</body>