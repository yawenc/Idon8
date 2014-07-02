<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Blah</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		List if charities that have been selected
		<div>
			<g:each in="${selectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>
		
		List of charities that have never been selected
		<div>
			<g:each in="${notSelectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>
		<div id="page-body" role="main">
			<g:link url="donation">Donate</g:link>
			<g:link url="register">Register</g:link>
			<g:form url='[controller: "index", action: "index"]' id="searchableForm" name="searchableForm" method="get">
		        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
		    </g:form>
		    <g:if test="${searchResult?.results}">
		      <div class="results">
		        <g:each var="result" in="${searchResult.results}" status="index">
		          <div class="result">
		            <g:link url="donation/index/${result.id}">${result.name}</g:link>
		          </div>
		        </g:each>
		      </div>
		
		      <div>
		        <div class="paging">
		          <g:if test="${haveResults}">
		              Page:
		              <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max)}" />
		              <g:if test="${totalPages == 1}"><span class="currentStep">1</span></g:if>
		              <g:else><g:paginate controller="searchable" action="index" params="[q: params.q]" total="${searchResult.total}" prev="&lt; previous" next="next &gt;"/></g:else>
		          </g:if>
		        </div>
		      </div>
		    </g:if>
			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>
