<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I-don8.org</title>
</head>
<body>

<h1><g:message code='spring.security.ui.login.signin'/></h1>
<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>

    <div class="fieldcontain">
        <label for="username">
            <g:message code='spring.security.ui.login.username'/>
        </label>
        <g:textField name="j_username" id="username" size="20" />
    </div>

    <div class="fieldcontain">
        <label for="password">
            <g:message code='spring.security.ui.login.password'/>
        </label>
        <g:passwordField name="j_password" id="password" size="20" />
    </div>

    <div class="fieldcontain">
        <label for="remember_me">
            <g:message code='spring.security.ui.login.rememberme'/>
        </label>
        <g:checkBox name="${rememberMeParameter}" id="remember_me" checked="true" />
    </div>

    <div class="fieldcontain">
        <g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
    </div>

    <div>
        <g:link controller="register" action="index"><g:message code="spring.security.ui.login.register" /></g:link>
    </div>

    <div>
        <g:submitButton name="login" value="${message(code: 'spring.security.ui.login.login')}" />
    </div>
</form>

<script>
$(document).ready(function() {
	$('#username').focus();
});

</script>

</body>
</html>
