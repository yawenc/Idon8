package com.bertazoli.charity.auth

import com.bertazoli.charity.user.UserDetails;
import com.bertazoli.charity.Donation

class User {

	transient springSecurityService

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasOne = [userDetails:UserDetails]
	static hasMany = [donations:Donation]
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false, nullable: false, email: true
		userDetails blank: true, nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
