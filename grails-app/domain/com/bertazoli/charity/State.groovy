package com.bertazoli.charity

class State {
	String code
	String name
	
	static belongsTo = [country:Country]
}
