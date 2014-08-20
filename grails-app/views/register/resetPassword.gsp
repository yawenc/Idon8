<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I-don8.org</title>
</head>
<body>

<h4><g:message code='spring.security.ui.resetPassword.description'/></h4>

<g:form action="resetPassword" name="resetPasswordForm" autocomplete="off">
    <g:hiddenField name='t' value='${token}'/>
    <div class="fieldcontain ${hasErrors(bean: command, field: 'password', 'error')}">
        <label for="password">
            <g:message code="resetPasswordCommand.password.label" default="Password" />
            <span class="required-indicator">*</span>
        </label>
        <g:passwordField name="password" size="25"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: command, field: 'password2', 'error')}">
        <label for="password2">
            <g:message code="resetPasswordCommand.password2.label" default="Password (again)" />
            <span class="required-indicator">*</span>
        </label>
        <g:passwordField name="password2" size="25" />
    </div>

    <g:submitButton name="resetPasswordForm" value="${message(code: 'spring.security.ui.resetPassword.submit')}" />
</g:form>

<script>
$(document).ready(function() {
	$('#password').focus();
});
</script>

</body>
</html>
