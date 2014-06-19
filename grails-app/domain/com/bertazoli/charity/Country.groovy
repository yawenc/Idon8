package com.bertazoli.charity

class Country {
	String name;
	String code;
	
	static hasMany = [states:State]

    static constraints = {
		name blank:false, nullable:false
		code blank:false, nullable:false, size:2..2
    }
}
