package com.bertazoli.charity.enums

enum CharitySanction {
	PENALIZED("Penalized"),
	SUSPENDED("Suspended");
	
	final String displayName
	
	CharitySanction(String displayName) {
		this.displayName = displayName
	}
	
	String getKey() {
		name()
	}
	
	String toString() {
		displayName
	}
}
