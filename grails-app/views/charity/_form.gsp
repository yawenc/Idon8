<%@ page import="com.bertazoli.charity.Charity" %>
<%@ page import="com.bertazoli.charity.enums.CharityStatus" %>
<%@ page import="com.bertazoli.charity.enums.CharitySanction" %>
<%@ page import="com.bertazoli.charity.enums.CharityDesignationCode" %>
<%@ page import="com.bertazoli.charity.enums.CharityCategory" %>


<div class="fieldcontain ${hasErrors(bean: charityInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="charity.address.label" default="Address" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${charityInstance?.address?}" var="a">
    <li><g:link controller="address" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="address" action="create" params="['charity.id': charityInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'address.label', default: 'Address')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: charityInstance, field: 'effectiveDateOfStatus', 'error')} required">
	<label for="effectiveDateOfStatus">
		<g:message code="charity.effectiveDateOfStatus.label" default="Effective Date Of Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="effectiveDateOfStatus" precision="day"  value="${charityInstance?.effectiveDateOfStatus}"  />
</div>

<div class="fieldcontain">
	<label for="status">
		<g:message code="charity.status.label" default="Status" />
	</label>
	<g:select name="status" from="${CharityStatus.values() }" value="${charityInstance?.status}" optionKey="key" noSelection="${['':''] }"/>
</div>

<div class="fieldcontain">
	<label for="sanction">
		<g:message code="charity.sanction.label" default="Sanction" />
	</label>
	<g:select name="sanction" from="${CharitySanction.values() }" value="${charityInstance?.sanction}" optionKey="key" noSelection="${['':''] }"/>
</div>

<div class="fieldcontain">
	<label for="designationCode">
		<g:message code="charity.designationCode.label" default="Designation Code" />
	</label>
	<g:select name="designationCode" from="${CharityDesignationCode.values() }" value="${charityInstance?.designationCode}" optionKey="key" noSelection="${['':''] }"/>
</div>

<div class="fieldcontain required">
	<label for="category">
		<g:message code="charity.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="category" from="${CharityCategory.values() }" value="${charityInstance?.category}" optionKey="key"/>
</div>

<div class="fieldcontain ${hasErrors(bean: charityInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="charity.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${charityInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: charityInstance, field: 'registrationNumber', 'error')} required">
	<label for="registrationNumber">
		<g:message code="charity.registrationNumber.label" default="Registration Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="registrationNumber" required="" value="${charityInstance?.registrationNumber}"/>

</div>

