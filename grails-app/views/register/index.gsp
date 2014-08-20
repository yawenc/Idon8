<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I-don8.org</title>
</head>
<body>

<g:form action='register' name='registerForm'>

	<g:if test='${emailSent}'>
	<br/>
	<g:message code='spring.security.ui.register.sent'/>
	<g:link url="/Charity">Click here to go back</g:link>
	</g:if>
	<g:else>

    <g:if test="${flash.error}">
        <div class="error" role="status">${flash.error}</div>
    </g:if>

	<div class="fieldcontain ${hasErrors(bean: command, field: 'username', 'error')} required">
        <label for="username">
            <g:message code="user.username.label" default="Username" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="username" value="${command.username}" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'firstName', 'error')} required">
        <label for="firstName">
            <g:message code="user.firstName.label" default="First Name" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="firstName" value="${command.firstName}" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'lastName', 'error')} required">
        <label for="lastName">
            <g:message code="user.lastName.label" default="Last Name" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="lastName" value="${command.lastName}" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'dateOfBirth', 'error')} required">
        <label for="dateOfBirth">
            <g:message code="user.dateOfBirth.label" default="Date of Birth" />
            <span class="required-indicator">*</span>
        </label>
	    <g:jqDatePicker name="dateOfBirth" value="${command.dateOfBirth}" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'email', 'error')} required">
        <label for="email">
            <g:message code="user.email.label" default="Email" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="email" value="${command.email}" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'password', 'error')} required">
        <label for="password">
            <g:message code="user.password.label" default="Password" />
            <span class="required-indicator">*</span>
        </label>
        <g:passwordField name="password" value="${command.password}" size="40" />
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: command, field: 'password2', 'error')} required">
        <label for="password2">
            <g:message code="user.password2.label" default="Password (again)" />
            <span class="required-indicator">*</span>
        </label>
        <g:passwordField name="password2" value="${command.password2}" size="40" />
	</div>

    <div>
        <g:submitButton name="create" value="${message(code: 'spring.security.ui.register.submit')}" />
    </div>

	</g:else>

</g:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
