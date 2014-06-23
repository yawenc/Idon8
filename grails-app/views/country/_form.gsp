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
	<g:textField name="code" maxlength="4" required="" value="${countryInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="country.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${countryInstance?.active}" />

</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'states', 'error')} ">
	<label for="states">
		<g:message code="country.states.label" default="States" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${countryInstance?.states?}" var="s">
    <li><g:link controller="state" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="state" action="create" params="['country.id': countryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'state.label', default: 'State')])}</g:link>
</li>
</ul>


</div>

