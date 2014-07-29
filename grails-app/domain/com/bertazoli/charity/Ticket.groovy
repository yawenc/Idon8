package com.bertazoli.charity

class Ticket {
	
	String ticketNumber
	
	static belongsTo = [donation:Donation]
}
