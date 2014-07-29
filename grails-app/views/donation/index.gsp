<%@ page import="com.bertazoli.charity.Donation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'donation.label', default: 'Donation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="/Charity/js/donation.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready( function() {
			$("#charitySearch").autocomplete({
				source : function(request, response) {
					$.ajax({
						url : "${request.contextPath}/charity/autoCompleteList", // remote datasource
						data : request,
						success : function(data) {
							response(data); // set the response
						},
						error : function() { // handle server errors
							$.jGrowl("Unable to retrieve Charities", {
								theme : 'ui-state-error ui-corner-all'
							});
						}
					});
				},
				minLength : 2, // triggered only after minimum 2 characters have been entered.
				select : function(event, ui) {
					$("#charity\\.id").val(ui.item.id); // update the hidden field.
				}
			});
		});
		</script>
	</head>
	<body>
		<g:if test="${charity != null}">
		    <h1><g:message code="donation.create.label" args="[charity?.name]" /></h1>
            <g:message code="charity.registrationNumber.label" default="Registration number" /> ${charity.registrationNumber}
		</g:if>
        <g:elseif test="${donationInstance != null && donationInstance.charity != null}">
            <h1><g:message code="donation.create.label" args="[donationInstance.charity?.name]" /></h1>
        </g:elseif>
		<g:else>
            <g:message code="default.searchForACharity.label" default="Search for a charity" />
		    <g:textField name="charitySearch"/>
		</g:else>
		
		<a href="#list-donation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>-->
			</ul>
		</div>
		<div id="list-donation" class="content scaffold-list" role="main">
			<h1><g:message code="donation.make.label"/></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${donationInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${donationInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form url="[resource:donationInstance, action:'save']" >
				<g:hiddenField name="charity.id" value="${charity != null ? charity.id : donationInstance != null ? donationInstance.charity?.id : null}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.donate.label', default: 'Donate')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
