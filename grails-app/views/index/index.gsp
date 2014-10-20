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

        <div class="hero">
            <div class="row">
                <h1>Donate and win heading</h1>
                <h4>Descriptive subheading text lorem ipsum lorem ipsum lorem ipsum</h4>
            </div>
        </div>

        <div class="search-bar-wrapper">
            <div class="row">
                <div class="append field">
                    <label>
                        <g:message code="default.searchForACharity.label" default="Search for a charity" />
                    </label>
                    <g:textField class="input wide" name="charitySearch"/>
                    <div class="medium primary btn"><a href="#">Go</a></div>
                </div>
            </div>
        </div>

    <div class="row margin-top-30">
        <div class="eight columns">
            <div class="promo-box">
                <div class="row">
                    <div class="four columns mission-logo">
                        <img src="${resource(dir: 'images', file: 'full-logo.png')}" alt="I-don8.org"/>
                    </div>
                    <div class="eight columns omega">
                        <h1>Our Mission</h1>
                        Improve the health and lives of people affected by poverty or emergency situations by mobilising and providing essential medical resources needed for their care.
                    </div>
                </div>
            </div>
        </div>
        <div class="four columns omega">
            <div>
                <h2><g:message code="index.donation.totalDonated" default="Total donated" /></h2>
                <g:formatNumber number="${totalDonated}" formatName="default.currency.format" />
            </div>
            <div class="medium primary btn"><g:link url="donation">Donate</g:link></div>

            <div>
                <span id="charityName"></span>
            </div>
        </div>

    </div>

    <div class="margin-top-20 home-page">
        <div class="row">
            <div class="eight columns">

                <h3><g:message code="index.howDoesItWork.label" default="How does it work" />?</h3>
                <g:message code="index.howItWorks.message" />

                <h3><g:message code="index.whyPaypal.label" default="Why paypal&#174;" />?</h3>
                <g:message code="index.whyPaypal.message" />

                <h3><g:message code="index.whatHappensToMyDonation.label" default="What happens to my donation" />?</h3>
                <g:message code="index.whatHappensToMyDonation.message" />

                <h3><g:message code="index.whereIsMyCharity.label" default="Where is my charity"  />?</h3>
                <g:message code="index.whereIsMyCharity.message" />

                <h3><g:message code="index.whatHappensIfTheCharityIChoseIsNoLongerActive.label" default="What happens if the charity I chose is no longer active" />?</h3>
                <g:message code="index.whatHappensIfTheCharityIChoseIsNotActive.message" />

            </div>
            <div class="four columns omega">
                <div id="chart2" style="margin-top:20px; margin-left:20px; width:200px; height:200px;"></div>
                <div>
                    <h3><g:message code="index.listOfSelectedCharities.label" default="List of charities that have been selected" /></h3>
                    <g:each in="${selectedCharities}" status="i" var="charity">
                        <li class="controller">
                            <g:link url="donation/index/${charity.id}">${charity.name}</g:link>
                        </li>
                    </g:each>
                </div>

                <div>
                    <h3><g:message code="index.listOfNeverSelectedCharities.label" default="List of charities that have never been selected" /></h3>
                    <g:each in="${notSelectedCharities}" status="i" var="charity">
                        <li class="controller">
                            <g:link url="donation/index/${charity.id}">${charity.name}</g:link>
                        </li>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
	</body>
</html>
