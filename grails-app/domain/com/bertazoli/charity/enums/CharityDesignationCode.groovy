package com.bertazoli.charity.enums

enum CharityDesignationCode {
	A("Public Foundation"),
	B("Private Foundation"),
	C("Charitable Organization");
	
	final String displayName
	
	CharityDesignationCode(String displayName) {
		this.displayName = displayName;
	}
	
	String getKey() {
		name()
	}
	
	String toString() {
		displayName
	}
}
