<%@ page import="com.bertazoli.charity.IndexController" %>
<!DOCTYPE html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 9]>    <html class="no-js ie9" lang="en"> <![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 9]><!--> <html class="no-js" lang="en" itemscope itemtype="http://schema.org/Product"> <!--<![endif]-->
	<head>
		<r:require module="jquery-ui"/>
		<r:layoutResources />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'gumby.css')}" type="text/css">
		<!-- Vitor how do I add the link below? -->
		<script src="js/libs/modernizr-2.6.2.min.js"></script>
		<g:layoutHead/>
		<g:javascript library="application"/>
	</head>
	<body>
        <div class="container sky">
            <div class="row">
                <div class="pull_right">
                    <sec:ifLoggedIn>
                        <div><g:message code="index.welcomeBack" args="${sec.username()}"/></div>
                        <div><g:link controller="logout">Logout</g:link></div>
                        <div><g:link controller="mySpace"><g:message code="index.mySpace.label" default="My Space" /></g:link></div>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <div><g:link controller="admin">Admin</g:link></div>
                        </sec:ifAllGranted>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <div><g:link controller="login">Login</g:link></div>
                        <div><g:link controller="register">Register</g:link></div>
                    </sec:ifNotLoggedIn>
                </div>
            </div>
        </div>

		<r:layoutResources />

        <div class="row">

            <div id="grailsLogo" role="banner">
                <g:link controller="index">
                    <img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="I-don8.org"/>
                </g:link>
            </div>
            <g:layoutBody/>
            <div class="clearfix"/>
            <div class="footer" role="contentinfo"></div>
            <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>


        </div>



        <!-- Grab Google CDN's jQuery, fall back to local if offline -->
        <!-- 2.0 for modern browsers, 1.10 for .oldie -->
        <script>
            var oldieCheck = Boolean(document.getElementsByTagName('html')[0].className.match(/\soldie\s/g));
            if(!oldieCheck) {
                document.write('<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"><\/script>');
            } else {
                document.write('<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"><\/script>');
            }
        </script>
        <script>
            if(!window.jQuery) {
                if(!oldieCheck) {
                    <!-- Vitor how do I add the link below? -->
                    document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
                } else {
                    <!-- Vitor how do I add the link below? -->
                    document.write('<script src="js/libs/jquery-1.10.1.min.js"><\/script>');
                }
            }
        </script>



        <!-- Vitor how do I add the links below? -->
        <script gumby-touch="js/libs" src="js/libs/gumby.js"></script>
        <script src="js/libs/ui/gumby.retina.js"></script>
        <script src="js/libs/ui/gumby.fixed.js"></script>
        <script src="js/libs/ui/gumby.skiplink.js"></script>
        <script src="js/libs/ui/gumby.toggleswitch.js"></script>
        <script src="js/libs/ui/gumby.checkbox.js"></script>
        <script src="js/libs/ui/gumby.radiobtn.js"></script>
        <script src="js/libs/ui/gumby.tabs.js"></script>
        <script src="js/libs/ui/gumby.navbar.js"></script>
        <script src="js/libs/ui/jquery.validation.js"></script>
        <script src="js/libs/gumby.init.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <!--[if lt IE 7 ]>
        <script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
        <script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
        <![endif]-->

	</body>
</html>
