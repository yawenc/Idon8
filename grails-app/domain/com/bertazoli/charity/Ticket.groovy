package com.bertazoli.charity

class Ticket {
	
	String ticketNumber
	
	static belongsTo = [donation:Donation]

    @Override
    String toString() {
        return ticketNumber.substring(0,10)
    }
}
