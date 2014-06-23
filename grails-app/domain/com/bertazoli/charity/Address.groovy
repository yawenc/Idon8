package com.bertazoli.charity

class Address {

	Country country
	State state
	String street
	String city
	String postalCode
	
	static belongsTo = [charity:Charity]
	
	@Override
	public String toString() {
		street + " " + city + " " + postalCode
	}
}
