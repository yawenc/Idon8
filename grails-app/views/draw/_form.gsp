<%@ page import="com.bertazoli.charity.Draw" %>



<div class="fieldcontain ${hasErrors(bean: drawInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="draw.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${drawInstance?.active}" />

</div>

<div class="fieldcontain ${hasErrors(bean: drawInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="draw.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${drawInstance?.endDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: drawInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="draw.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${drawInstance?.startDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: drawInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="draw.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${com.bertazoli.charity.enums.DrawStatus?.values()}" keys="${com.bertazoli.charity.enums.DrawStatus.values()*.name()}" required="" value="${drawInstance?.status?.name()}" />

</div>

