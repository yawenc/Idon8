package com.bertazoli.charity.user

import com.bertazoli.charity.auth.User

class UserDetails {
	String firstName
	String lastName
	Date dateOfBirth
	
	static belongsTo = [user:User]
}
