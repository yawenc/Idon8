<%@ page import="com.bertazoli.charity.FundRaising" %>

<div class="fieldcontain ${hasErrors(bean: fundRaisingInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="fundRaising.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
    <g:textArea name="description" value="${fundRaisingInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fundRaisingInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="fundRaising.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
    <g:jqDatePicker name="startDate" value="${fundRaisingInstance?.startDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fundRaisingInstance, field: 'endDate', 'error')} required">
    <label for="endDate">
        <g:message code="fundRaising.endDate.label" default="End Date" />
        <span class="required-indicator">*</span>
    </label>
    <g:jqDatePicker name="endDate" value="${fundRaisingInstance?.endDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fundRaisingInstance, field: 'privateFundRaising', 'error')} required">
    <label for="privateFundRaising">
        <g:message code="fundRaising.private.label" default="Private" />
        <span class="required-indicator">*</span>
    </label>
    <g:checkBox name="privateFundRaising" value="${fundRaisingInstance?.privateFundRaising}" />
</div>

<div class="fieldcontain ${hasErrors(bean: fundRaisingInstance, field: 'image', 'error')} required">
    <label for="image">
        <g:message code="fundRaising.image.label" default="Image" />
        <span class="required-indicator">*</span>
    </label>
    <input type="file" name="image" id="image"/>
</div>
