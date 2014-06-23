<%@ page import="com.bertazoli.charity.State" %>



<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="state.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${stateInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="state.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${com.bertazoli.charity.Country.list()}" optionKey="id" required="" value="${stateInstance?.country?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="state.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${stateInstance?.name}"/>

</div>

