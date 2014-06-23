<%@ page import="com.bertazoli.charity.Address" %>



<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'charity', 'error')} required">
	<label for="charity">
		<g:message code="address.charity.label" default="Charity" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="charity" name="charity.id" from="${com.bertazoli.charity.Charity.list()}" optionKey="id" required="" value="${addressInstance?.charity?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="address.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${addressInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="address.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${com.bertazoli.charity.Country.list()}" optionKey="id" required="" value="${addressInstance?.country?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'postalCode', 'error')} required">
	<label for="postalCode">
		<g:message code="address.postalCode.label" default="Postal Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="postalCode" required="" value="${addressInstance?.postalCode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'state', 'error')} required">
	<label for="state">
		<g:message code="address.state.label" default="State" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="state" name="state.id" from="${com.bertazoli.charity.State.list()}" optionKey="id" required="" value="${addressInstance?.state?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'street', 'error')} required">
	<label for="street">
		<g:message code="address.street.label" default="Street" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="street" required="" value="${addressInstance?.street}"/>

</div>

