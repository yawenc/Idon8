import com.bertazoli.charity.auth.Role
import com.bertazoli.charity.auth.User
import com.bertazoli.charity.auth.UserRole
import com.bertazoli.charity.user.UserDetails

class BootStrap {

    def init = { servletContext ->
		
		addCountries()
		
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def userDetails = new UserDetails(firstName: 'Vitor', lastName: 'Bertazoli', dateOfBirth: new Date()).save(flush: true)
		def testUser = new User(username: 'vitor', password: '1q2w3e4r', email: 'vitor@bertazoli.com', userDetails: userDetails)
		testUser.save(flush: true)

		UserRole.create testUser, adminRole, true
		UserRole.create testUser, userRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 2
    }
    def destroy = {
    }
	
	void addCountries() {

	}
}
