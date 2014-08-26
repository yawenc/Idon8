<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>I-don8.org</title>
		<script src="${resource(dir: 'js', file: 'index.js')}" type="text/javascript"></script>
	</head>
	<body>
		<div id="page-body" role="main">
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
