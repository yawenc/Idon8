package com.bertazoli.charity

class State {
	String code
	String name
	
	static belongsTo = [country:Country]
	
	@Override
	public String toString() {
		return name;
	}
}
