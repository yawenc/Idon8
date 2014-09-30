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
		<script src="${resource(dir: 'js/libs', file: 'modernizr-2.6.2.min.js')}" type="text/javascript"></script>

        <script gumby-touch="js/libs" src="${resource(dir: 'js/libs', file: 'gumby.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.retina.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.fixed.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.skiplink.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.toggleswitch.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.checkbox.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.radiobtn.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.tabs.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'gumby.navbar.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs/ui', file: 'jquery.validation.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/libs', file: 'gumby.init.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'plugins.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'main.js')}" type="text/javascript"></script>

        <!--[if lt IE 7 ]>
        <script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
        <script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
        <![endif]-->

		<g:layoutHead/>
		<g:javascript library="application"/>
	</head>
	<body>
        <div class="container sky">
            <div class="row">
                <div class="pull_left">
                    <g:link controller="index">
                        <img src="${resource(dir: 'images', file: 'logo.png')}" alt="I-don8.org"/>
                    </g:link>
                </div>
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
                        <div class="small"><g:link controller="login">Login</g:link> | <g:link controller="register">Register</g:link></div>
                    </sec:ifNotLoggedIn>
                </div>
            </div>
        </div>

		<r:layoutResources />

        <div class="row">
            <g:layoutBody/>
            <div class="footer" role="contentinfo"></div>
            <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
        </div>
	</body>
</html>
