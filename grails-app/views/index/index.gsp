<!DOCTYPE html>
<html>
	<head>
        <r:require modules="jqPlot" />
		<meta name="layout" content="main"/>
		<title>I-don8.org</title>
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
		<script src="${resource(dir: 'js', file: 'index.js')}" type="text/javascript"></script>
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
					$("#charityName").text(ui.item.id);
					window.location.replace('donation/index/'+ui.item.id);
				}
			});

            var ajaxDataRenderer = function(url, plot, options) {
                var ret = null;
                $.ajax({
                    async: false,
                    url: url,
                    dataType: "json",
                    success: function(data) {
                        ret = data;
                    }
                })
                return ret;
            };
            var serverURL = "index/percentageToKeep"
            var plot = $.jqplot('chart2', serverURL, {
               title: "Percentage people selected to keep to themselves",
                dataRenderer: ajaxDataRenderer,
                seriesDefaults: {
                    renderer: $.jqplot.PieRenderer,
                    rendererOptions: {showDataLabels: true}
                },
                legend:{
                    show:true,
                    placement: 'outside',
                    rendererOptions: {
                        numberRows: 1
                    },
                    location:'s',
                    marginTop: '15px'
                },
                dataRendererOptions: {
                    unusedOptionalUrl: serverURL
                }
            });
		});
		</script>
	</head>
	<body>
		<sec:ifLoggedIn>
			<g:message code="index.welcomeBack" args="${sec.username()}"/><br/>
            <g:link controller="logout">Logout</g:link><br/>
            <g:link controller="donation" action="myDonations"><g:message code="index.myDonations" default="My donations"/></g:link><br/>
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<g:link controller="admin">Admin</g:link><br/>
			</sec:ifAllGranted>
		</sec:ifLoggedIn>
		<sec:ifNotLoggedIn>
			<g:link controller="login">Login</g:link><br/>
			<g:link controller="register">Register</g:link><br/>
		</sec:ifNotLoggedIn>
        <g:message code="index.listOfSelectedCharities.label" default="List of charities that have been selected" />
		<div>
			<g:each in="${selectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>

        <g:message code="index.listOfNeverSelectedCharities.label" default="List of charities that have never been selected" />
		<div>
			<g:each in="${notSelectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>

        <div>
            <g:message code="index.donation.totalDonated" default="Total donated" />
            <g:formatNumber number="${totalDonated}" type="number" formatName="default.currency.format" />
        </div>
		<div id="page-body" role="main">
			<g:link url="donation">Donate</g:link>
			<div>
            <g:message code="index.searchForACharity.label" default="Search for a charity" />
            <g:textField name="charitySearch"/>
			</div>
		    <div>
		    	<span id="charityName"></span>
		    </div>
		</div>
        <div id="chart2" style="margin-top:20px; margin-left:20px; width:200px; height:200px;"></div>
	</body>
</html>
