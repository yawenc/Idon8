$(document).ready( function() {
	$("#charitySearch").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/Charity/charity/autoCompleteList", // remote datasource
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
		select : function(event, ui) { // event handler when user selects a company from the list.
			$("#charity\\.id").val(ui.item.id); // update the hidden field.
		}
	});
});