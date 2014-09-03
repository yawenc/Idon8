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

            /*
            $("#userFundRaisingSearch").autocomplete({
                source : function(request, response) {
                    $.ajax({
                        url : "${request.contextPath}/fundRaising/autoCompleteList", // remote datasource
                        data : request,
                        success : function(data) {
                            response(data); // set the response
                        },
                        error : function() { // handle server errors
                            $.jGrowl("Unable to retrieve user's fund raising list", {
                                theme : 'ui-state-error ui-corner-all'
                            });
                        }
                    });
                },
                minLength : 2, // triggered only after minimum 2 characters have been entered.
                select : function(event, ui) {
                    window.location.replace('donation/userFundRaising/'+ui.item.id);
                }
            });
            */

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

        <div class="hero container">
            Main Banner Image goes here
        </div>

        <div>
            <h1><g:message code="index.howDoesItWork.label" default="How does it work" />?</h1>
            <g:message code="index.howItWorks.message" />
        </div>

        <div>
            <h1><g:message code="index.whyPaypal.label" default="Why paypal&#174;" />?</h1>
            <g:message code="index.whyPaypal.message" />
        </div>

        <div>
            <h1><g:message code="index.whatHappensToMyDonation.label" default="What happens to my donation" />?</h1>
            <g:message code="index.whatHappensToMyDonation.message" />
        </div>

        <div>
            <h1><g:message code="index.whereIsMyCharity.label" default="Where is my charity"  />?</h1>
            <g:message code="index.whereIsMyCharity.message" />
        </div>

        <div>
            <h1><g:message code="index.whatHappensIfTheCharityIChoseIsNoLongerActive.label" default="What happens if the charity I chose is no longer active" />?</h1>
            <g:message code="index.whatHappensIfTheCharityIChoseIsNotActive.message" />
        </div>

		<div>
            <h1><g:message code="index.listOfSelectedCharities.label" default="List of charities that have been selected" /></h1>
			<g:each in="${selectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>

		<div>
            <h1><g:message code="index.listOfNeverSelectedCharities.label" default="List of charities that have never been selected" /></h1>
            <g:each in="${notSelectedCharities}" status="i" var="charity">
				<li class="controller">
					<g:link url="donation/index/${charity.id}">${charity.name}</g:link>
				</li>
			</g:each>
		</div>

        <div>
            <h2><g:message code="index.donation.totalDonated" default="Total donated" /></h2>
            <g:formatNumber number="${totalDonated}" formatName="default.currency.format" />
        </div>
		<div id="page-body" role="main">
			<g:link url="donation">Donate</g:link>
			<div>
                <g:message code="default.searchForACharity.label" default="Search for a charity" />
                <g:textField name="charitySearch"/>
			</div>
		    <div>
		    	<span id="charityName"></span>
		    </div>
		</div>
        <div id="chart2" style="margin-top:20px; margin-left:20px; width:200px; height:200px;"></div>
	</body>
</html>
