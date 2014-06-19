<%@ page import="com.bertazoli.charity.Country" %>



<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="country.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${countryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="country.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="2" required="" value="${countryInstance?.code}"/>

</div>

